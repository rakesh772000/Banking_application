package com.banking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.model.Account;
import com.banking.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;
	
	public Account createAccount(Account account) {
		return accountRepository.save(account);
	}
	
	public Optional<Account> getAccount(Long id){
		return accountRepository.findById(id);
	}
	
	public Account deposit(Long id,double amount) {
		Account account=getAccount(id).orElseThrow(()->new RuntimeException("Account not found"));
		account.setBalance(account.getBalance()+amount);
		return accountRepository.save(account);
	}
	
	public Account withdraw(long id,double amount) {
		Account account = getAccount(id).orElseThrow(()->new RuntimeException("Account not found"));
		if(account.getBalance()<amount) {
			throw new RuntimeException("Insufficient funds");
		}
		account.setBalance(account.getBalance()-amount);
		return accountRepository.save(account);
	}
	
	public List<Account> getallaccounts(Account account){
		return accountRepository.findAll();
	}

}
