package com.example.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entity.Item;
import com.example.backend.repository.ItemRepository;

@RestController
public class ItemController {
	
	private final ItemRepository itemRepository;
	
	public ItemController(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@CrossOrigin(origins = "http://localhost:3000") // Vue 개발 서버 도메인
	@GetMapping("/api/items")
	public List<Item> getItems() {

		List<Item> items = itemRepository.findAll();
		System.out.printf("items: ", items);
	
		return items;
	}
}
