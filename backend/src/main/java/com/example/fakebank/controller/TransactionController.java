package com.example.fakebank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fakebank.dto.TransactionResponse;
import com.example.fakebank.service.TransactionService;

// REST Controller for transaction Operations
@RestController
@RequestMapping("api/accounts")
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;
	
	// GET ALL transactions for an account
	@GetMapping("/{id}/transactions")
	public ResponseEntity<List<TransactionResponse>> getTransactions(@PathVariable Long id){
		List<TransactionResponse> transactions = transactionService.getTransactionsByAccountId(id);
		return ResponseEntity.ok(transactions);
	}
}
