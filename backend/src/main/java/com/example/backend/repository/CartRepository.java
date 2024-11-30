package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.Cart;
import java.util.List;


public interface CartRepository extends JpaRepository<Cart, Integer> {
	
	List<Cart> findByMemberId(int memberId);
	
	Cart findByMemberIdAndItemId(int memberId, int itemId);
}
