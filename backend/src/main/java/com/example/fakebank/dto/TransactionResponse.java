package com.example.fakebank.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.fakebank.entity.TransactionType;

// DTO for returning transaction info to the frontend
public class TransactionResponse {
	
	private Long id;
	private TransactionType type;
	private BigDecimal amount;
	private LocalDateTime date;
	
	// Constructors
	public TransactionResponse() {}
	public TransactionResponse(Long id, TransactionType type, BigDecimal amount, LocalDateTime date) {
		this.id = id;
		this.type = type;
		this.amount = amount;
		this.date = date;
	}
	
	// Getters and Setters
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public TransactionType getType() {
		return type;
	}
	public void setType(TransactionType type) {
		this.type = type;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}
