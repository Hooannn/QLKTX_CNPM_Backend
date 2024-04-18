package com.ht.qlktx.modules.account;

import com.ht.qlktx.annotations.RequiredRole;
import com.ht.qlktx.config.Response;
import com.ht.qlktx.entities.Account;
import com.ht.qlktx.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping
    @RequiredRole({Role.STAFF, Role.ADMIN})
    public ResponseEntity<Response<List<Account>>> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return ResponseEntity.ok().body(new Response<>(
                HttpStatus.OK.value(),
                "Danh sách tài khoản",
                accounts
        ));
    }
}
