package com.example.fakebank.dto;

import java.math.BigDecimal;

public class BalanceResponse {
	private Long accountId;
	private String accountName;
	private BigDecimal balance;
	
	// Constructors
	public BalanceResponse() {}
	public BalanceResponse(Long accountId, String accountName, BigDecimal balance) {
		this.accountId = accountId;
		this.accountName = accountName;
		this.balance = balance;
	}
	
	// Getters and Setters
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	
}
