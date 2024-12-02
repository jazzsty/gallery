package com.example.backend.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private int memberId;
	
	@Column(length = 50, nullable = false)
	private String name;
	
	@Column(length = 200, nullable = false)
	private String address;
	
	@Column(length = 10, nullable = false)
	private String payment;
	
	@Column(length = 16)
	private String cardNumber;
	
	@Column(length = 500, nullable = false)
	private String items;
	
	@UpdateTimestamp
    @Column(name = "updateAt", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updateAt;
}
