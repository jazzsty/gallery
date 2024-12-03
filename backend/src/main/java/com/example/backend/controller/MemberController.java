package com.example.backend.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.backend.entity.Member;
import com.example.backend.repository.MemberRepository;
import com.example.backend.service.JwtService;
import com.example.backend.service.JwtServiceImpl;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class MemberController {
	
	private final MemberRepository memberRepository;
	private final JwtService jwtService;
	
	public MemberController(MemberRepository memberRepository, JwtService jwtService) {
		this.memberRepository = memberRepository;
		this.jwtService = jwtService;
	}
	
	@PostMapping("/api/member/login")
	public ResponseEntity<Integer> login(@RequestBody Map<String, String> params,
			HttpServletResponse res) {
		System.out.println("check step login");
		Member member = memberRepository.findByEmailAndPassword(params.get("email"), params.get("password"));
		
		if (member != null) {
			System.out.println("member: " + member);
			int id = member.getId();
			String token = jwtService.getToken("id", id);
			
			Cookie cookie = new Cookie("token", token);
			cookie.setHttpOnly(true);
			cookie.setPath("/");
			
			res.addCookie(cookie);
			
			return new ResponseEntity<>(id, HttpStatus.OK);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/api/member/logout")
	public ResponseEntity<Void> login(HttpServletResponse res) {
		System.out.println("check step login");
		Cookie cookie = new Cookie("token", null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		
		res.addCookie(cookie);

		return new ResponseEntity<>(HttpStatus.OK);
	
	}

	@GetMapping("/api/member/check")
	public ResponseEntity<Integer> check(@CookieValue(value = "token", required = false) String token) {
		System.out.println("check step check");
		Claims claims = jwtService.getClaims(token);
		System.out.println("claims: " + claims);
		if(claims != null) {
			int id = Integer.parseInt(claims.get("id").toString());
			return new ResponseEntity<>(id, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
}
