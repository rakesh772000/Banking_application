package com.banking.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.banking.model.Account;
import com.banking.service.AccountService;

@RestController
@RequestMapping("/api")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	@PostMapping("/account")
	public ResponseEntity<Account> createAccount(@RequestBody Account account){
		Account createdAccount=accountService.createAccount(account);
		return ResponseEntity.ok(createdAccount);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Account> getAccount(@PathVariable long id){
		Account getaccount = accountService.getAccount(id).orElseThrow(()->new RuntimeException("Account not found"));
		return ResponseEntity.ok(getaccount);
	}
	@PostMapping("/{id}/deposit")
	public ResponseEntity<Account> deposit(@PathVariable long id, @RequestBody Map<String, Double> request){
		Double amount=request.get("amount");
		Account deposit = accountService.deposit(id,amount);
		return ResponseEntity.ok(deposit);
	}
	@PostMapping("/{id}/withdraw")
	public ResponseEntity<Account> withdraw(@PathVariable long id,@RequestBody Map<String, Double> request){
		Double withdraw = request.get("amount");
		Account amount1 = accountService.withdraw(id,withdraw);
		return ResponseEntity.ok(amount1);
	}
	@GetMapping("/account")
	public ResponseEntity<List<Account>> getallaccounts(@RequestBody Account account){
		List<Account> accountlist = accountService.getallaccounts(account);
		return ResponseEntity.ok(accountlist);
		
	}

}
