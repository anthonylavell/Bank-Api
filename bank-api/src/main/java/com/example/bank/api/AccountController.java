package com.example.bank.api;

import com.example.bank.api.dto.AccountResponse;
import com.example.bank.api.dto.CreateAccountRequest;
import com.example.bank.api.dto.MoneyRequest;
import com.example.bank.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@Validated
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(
            @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey,
            @Valid @RequestBody CreateAccountRequest body
    ) {
        // In production, persist the Idempotency-Key with a request hash and response payload.
        // Here we just ignore it to keep the sample concise.
        AccountResponse resp = service.create(body.ownerName());
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @GetMapping("/{id}")
    public AccountResponse get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/{id}/deposit")
    public AccountResponse deposit(@PathVariable Long id, @Valid @RequestBody MoneyRequest body) {
        return service.deposit(id, body.amount());
    }

    @PostMapping("/{id}/withdraw")
    public AccountResponse withdraw(@PathVariable Long id, @Valid @RequestBody MoneyRequest body) {
        return service.withdraw(id, body.amount());
    }
}
