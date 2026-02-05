package com.example.fakebank.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fakebank.dto.TransactionResponse;
import com.example.fakebank.entity.Account;
import com.example.fakebank.entity.Transaction;
import com.example.fakebank.entity.TransactionType;
import com.example.fakebank.repository.TransactionRepository;


@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	// Record a new transaction
	
	public Transaction recordTransaction(Account account, TransactionType type, BigDecimal amount) {
		Transaction transaction = new Transaction(account, type, amount);
		return transactionRepository.save(transaction);
	}
	
	// Get all transactions for an account
	
	public List<TransactionResponse> getTransactionsByAccountId(Long accountId){
		List<Transaction> transactions = transactionRepository.findByAccountIdOrderByDateDesc(accountId);
		
		// Convert entities to DTOs
		return transactions.stream().map(t -> new TransactionResponse(
				t.getId(),
				t.getType(),
				t.getAmount(),
				t.getDate()
				
				
				))
				.collect(Collectors.toList());
	}
}
