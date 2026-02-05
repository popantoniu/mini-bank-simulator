package com.example.fakebank.dto;

import java.math.BigDecimal;

public class TransactionRequest {
	private BigDecimal amount;
	
	//Constructors
	public TransactionRequest() {}
	public TransactionRequest(BigDecimal amount) {
		this.amount = amount;
	}
	// Getters and Setters
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
