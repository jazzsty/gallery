package com.example.backend.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.backend.entity.Member;
import com.example.backend.repository.MemberRepository;

@RestController
public class MemberController {
	
	private final MemberRepository memberRepository;
	
	public MemberController(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	@PostMapping("/api/member/login")
	public int login(@RequestBody Map<String, String> params) {
		System.out.println("check step login");
		Member member = memberRepository.findByEmailAndPassword(params.get("email"), params.get("password"));
		
		if (member != null) {
			System.out.println("member: " + member);
			return member.getId();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

}
