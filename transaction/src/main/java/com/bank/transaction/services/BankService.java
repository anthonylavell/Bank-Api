package com.bank.transaction.services;

import com.bank.transaction.domains.AccountProcess;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {
    private final AccountProcess accountProcess;

    public BankService(AccountProcess accountProcess) {
        this.accountProcess = accountProcess;
    }
    @Transactional
    public List<String>process(List<String>commands){
        return accountProcess.process(commands);
    }
}
