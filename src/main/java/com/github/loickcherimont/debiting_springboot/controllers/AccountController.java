package com.github.loickcherimont.debiting_springboot.controllers;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.loickcherimont.debiting_springboot.dto.DebitAmountDto;
import com.github.loickcherimont.debiting_springboot.models.Account;
import com.github.loickcherimont.debiting_springboot.services.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Account> debitAccountById(@PathVariable UUID id, @RequestBody DebitAmountDto debitAmountDto) {
        Account account = accountService.debitAccountById(id, debitAmountDto);

        return ResponseEntity.created(URI.create("http://localhost:8080/api/accounts/" + id)).body(account);
    }
}
