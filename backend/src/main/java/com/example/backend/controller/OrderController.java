package com.example.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.backend.dto.OrderDto;
import com.example.backend.entity.Order;
import com.example.backend.repository.OrderRepository;
import com.example.backend.service.JwtService;

@RestController
public class OrderController {
	
	private final JwtService jwtService;
	private final OrderRepository orderRepository;
	
	public OrderController(JwtService jwtService, OrderRepository orderRepository) {
		this.jwtService = jwtService;
		this.orderRepository = orderRepository;
	}
	
	@GetMapping("/api/orders")
	public ResponseEntity<List<Order>> getOrder(
			@CookieValue(value = "token", required = false) String token
	){
		if(token == null || !jwtService.isValid(token)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		
		List<Order> orders = orderRepository.findAll();
		
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	
	@PostMapping("/api/orders")
	public ResponseEntity<Void> pushOrder(
			@RequestBody OrderDto dto,
			@CookieValue(value = "token", required = false) String token
	){
		if(token == null || !jwtService.isValid(token)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		
		Order newOrder = new Order();
		System.out.println("memberId(): " + jwtService.getId(token));
//		newOrder.setMemberId(dto.getMemberId());
		newOrder.setMemberId(jwtService.getId(token));
		newOrder.setName(dto.getName());
		newOrder.setAddress(dto.getAddress());
		newOrder.setPayment(dto.getPayment());
		newOrder.setCardNumber(dto.getCardNumber());
		newOrder.setItems(dto.getItems());
//		newOrder.setUpdateAt(dto.getUpdateAt());
		
		orderRepository.save(newOrder);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
