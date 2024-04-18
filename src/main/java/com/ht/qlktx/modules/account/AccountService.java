package com.ht.qlktx.modules.account;

import com.ht.qlktx.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public boolean isEmailExists(String email) {
        return accountRepository.existsByEmail(email);
    }

    public Optional<Account> getAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public Optional<Account> getAccountByUsernameOrEmail(String username, String email) {
        return accountRepository.findByUsernameOrEmail(username, email);
    }

    // Add methods for creating, updating, and deleting accounts if needed
}
