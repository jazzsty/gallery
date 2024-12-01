package com.example.backend.controller;

import java.util.List;

//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.backend.entity.Cart;
import com.example.backend.entity.Item;
import com.example.backend.repository.CartRepository;
import com.example.backend.repository.ItemRepository;
import com.example.backend.service.JwtService;

@RestController
public class CartController {
	
	private final CartRepository cartRepository;
	private final ItemRepository itemRepository;
	private final JwtService jwtService;
	
	public CartController(CartRepository cartRepository, ItemRepository itemRepository, JwtService jwtService) {
		this.cartRepository = cartRepository;
		this.itemRepository = itemRepository;
		this.jwtService = jwtService;
	}
	
	@PostMapping("/api/cart/items/{itemId}")
	public ResponseEntity<Void> pushCartItem(
			@PathVariable("itemId") int itemId,
			@CookieValue(value = "token", required = false) String token
	){
		System.out.println("check step pushCartItem itemId:" + itemId);
		if(!jwtService.isValid(token)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		
		int memberId = jwtService.getId(token);
		Cart cart = cartRepository.findByMemberIdAndItemId(memberId, itemId);
		if(cart == null) {
			Cart newCart = new Cart();
			newCart.setMemberId(memberId);
			newCart.setItemId(itemId);
			cartRepository.save(newCart);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/api/cart/items")
	public ResponseEntity<List<Item>> getCartItems(
			@CookieValue(value = "token", required = false) String token
		){
		if(!jwtService.isValid(token)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		int memberId = jwtService.getId(token);
		List<Cart> carts = cartRepository.findByMemberId(memberId);
		List<Integer> itemIds = carts.stream().map(Cart::getItemId).toList();
		List<Item> items = itemRepository.findByIdIn(itemIds);
		
		return new ResponseEntity<>(items, HttpStatus.OK);
	}
	
	@DeleteMapping("/api/cart/items/{itemId}")
	public ResponseEntity<Void> removeCartItem(
			@PathVariable("itemId") int itemId,
			@CookieValue(value = "token", required = false) String token
	){
		System.out.println("check step removeCartItem itemId:" + itemId);
		if(!jwtService.isValid(token)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		
		int memberId = jwtService.getId(token);
		Cart cart = cartRepository.findByMemberIdAndItemId(memberId, itemId);
		System.out.println("cart: " + cart);
		cartRepository.delete(cart);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
