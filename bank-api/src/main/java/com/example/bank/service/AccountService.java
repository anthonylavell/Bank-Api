package com.example.bank.service;

import com.example.bank.api.dto.AccountResponse;
import com.example.bank.domain.Account;
import com.example.bank.repo.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class AccountService {
    private final AccountRepository repo;

    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public AccountResponse create(String ownerName) {
        Account a = new Account();
        a.setOwnerName(ownerName);
        a.setAccountNumber(generateUniqueNumber());
        a.setBalance(BigDecimal.ZERO);
        Account saved = repo.save(a);
        return map(saved);
    }

    @Transactional(readOnly = true)
    public AccountResponse getById(Long id) {
        return repo.findById(id).map(this::map)
                .orElseThrow(() -> new NoSuchElementException("Account not found: " + id));
    }

    @Transactional
    public AccountResponse deposit(Long id, BigDecimal amount) {
        Account a = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Account not found: " + id));
        a.setBalance(a.getBalance().add(amount));
        return map(a);
    }

    @Transactional
    public AccountResponse withdraw(Long id, BigDecimal amount) {
        Account a = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Account not found: " + id));
        if (a.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        a.setBalance(a.getBalance().subtract(amount));
        return map(a);
    }

    private String generateUniqueNumber() {
        // Simple: UUID without dashes. Replace with a proper number generator in prod.
        String v;
        do { v = UUID.randomUUID().toString().replace("-", ""); }
        while (repo.existsByAccountNumber(v));
        return v;
    }

    private AccountResponse map(Account a) {
        return new AccountResponse(a.getId(), a.getAccountNumber(), a.getOwnerName(),
                a.getBalance(), a.getCreatedAt());
    }
}
