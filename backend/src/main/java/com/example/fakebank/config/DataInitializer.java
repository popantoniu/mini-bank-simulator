package com.example.fakebank.config;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.fakebank.entity.Account;
import com.example.fakebank.repository.AccountRepository;

// this class runs when application starts
// it creates a test account if none exists

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public void run(String... args) throws Exception{
		//check if there are any accounts
		if(accountRepository.count() == 0) {
			// Create test account
			Account account = new Account("Joshua's Account");
			account.setBalance(new BigDecimal("1000.00"));
			accountRepository.save(account);
			
			System.out.println("==================================");
			System.out.println("Test account created with ID:" + account.getId());
			System.out.println("Account Name: " + account.getName());
			System.out.println("Initial Balance: $" + account.getBalance());
			System.out.println("==================================");
			
		}
	}
}
