package com.example.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.backend.service.JwtService;

@RestController
public class OrderController {
	
	private final JwtService jwtService;
	
	public OrderController(JwtService jwtService) {
		this.jwtService = jwtService;
	}
	
	@PostMapping("/api/orders")
	public ResponseEntity<Void> pushCartItem(
			@CookieValue(value = "token", required = false) String token
	){
		if(!jwtService.isValid(token)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
