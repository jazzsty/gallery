package com.example.backend.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.backend.entity.Member;
import com.example.backend.repository.MemberRepository;

public class MemberController {
	
	private final MemberRepository memberRepository;
	
	public MemberController(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	@PostMapping("/api/member/login")
	public int login(@RequestBody Map<String, String> params) {
		
		Member member = memberRepository.findByEmailAndPassword(params.get("email"), params.get("password"));
		return member.getId();
	}

}
