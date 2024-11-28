package com.example.backend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

	@CrossOrigin(origins = "http://localhost:3000") // Vue 개발 서버 도메인
	@GetMapping("/api/items")
	public List<String> getItems() {
		List<String> items = new ArrayList<>();
		items.add("alpha");
		items.add("bata");
		items.add("gamma");
	
		return items;
	}
}
