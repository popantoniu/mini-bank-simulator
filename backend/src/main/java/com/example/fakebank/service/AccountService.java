package com.example.fakebank.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fakebank.dto.BalanceResponse;
import com.example.fakebank.entity.Account;
import com.example.fakebank.entity.TransactionType;
import com.example.fakebank.repository.AccountRepository;

@Service
public class AccountService {
	
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionService transactionService;
	
	// Get account Balance
	
	public BalanceResponse getBalance(Long accountId) {
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new RuntimeException("Account not found with id: " + accountId));
		return new BalanceResponse(account.getId(), account.getName(), account.getBalance());
	}
	
	// Deposit money into account
	@Transactional
	public BalanceResponse deposit(Long accountId, BigDecimal amount) {
		//Validation
		if(amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new RuntimeException("Deposit must be positive");
			
		}
		// Get account
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new RuntimeException("Account not found with id: " + accountId));
		
		// Update the balance
		BigDecimal newBalance = account.getBalance().add(amount);
		account.setBalance(newBalance);
		accountRepository.save(account);
		
		// Record  transaction
		
		transactionService.recordTransaction(account, TransactionType.DEPOSIT, amount);
		
		return new BalanceResponse(account.getId(), account.getName(), account.getBalance());
	}
	
	// Withdraw money from account
	
	@Transactional
	public BalanceResponse withdraw(Long accountId, BigDecimal amount) {
		// Validation
		if(amount.compareTo(BigDecimal.ZERO) <=0) {
			throw new RuntimeException("Withrawal amount must be positive");
			
		}
		
		// Get Account
		
		Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found with id: " + accountId));
		
		// Check sufficient Balance
		if(account.getBalance().compareTo(amount) < 0) {
			throw new RuntimeException("insufficient balance. Current Balance: " + account.getBalance());
		}
		
		// Update the Balance
		BigDecimal newBalance = account.getBalance().subtract(amount);
		account.setBalance(newBalance);
		accountRepository.save(account);
		
		// Record transaction
		transactionService.recordTransaction(account, TransactionType.WITHDRAW, amount);
		
		return new BalanceResponse(account.getId(), account.getName(), account.getBalance());
	}
	
	// CREATE NEW ACCOUNT (For Testing)
	
	public Account createAccount(String name) {
		Account account = new Account(name);
		return accountRepository.save(account);
	}
}
