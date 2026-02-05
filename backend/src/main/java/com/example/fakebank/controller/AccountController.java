package com.example.fakebank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fakebank.dto.BalanceResponse;
import com.example.fakebank.dto.TransactionRequest;
import com.example.fakebank.service.AccountService;

// REST Controller for Account Operations
@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	// GET the current balance of an account
	@GetMapping("/{id}/balance")
	public ResponseEntity<BalanceResponse> getBalance(@PathVariable Long id){
		try {
			BalanceResponse balance = accountService.getBalance(id);
			return ResponseEntity.ok(balance);
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	// POST money into an account
	@PostMapping("/{id}/deposit")
	public ResponseEntity<?> deposit(@PathVariable Long id, @RequestBody TransactionRequest request){
		try {
			BalanceResponse balance = accountService.deposit(id,  request.getAmount());
			return ResponseEntity.ok(balance);
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	// POST withdraw money from account
	
	@PostMapping("/{id}/withdraw")
	public ResponseEntity<?> withdraw(@PathVariable Long id, @RequestBody TransactionRequest request){
		try {
			BalanceResponse balance = accountService.withdraw(id, request.getAmount());
			return ResponseEntity.ok(balance);
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
}
