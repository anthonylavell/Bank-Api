package com.bank.transaction.services;

import com.bank.transaction.domains.AccountProcess;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {
    @Transactional
    public List<String>process(List<String>commands){
        AccountProcess accountProcess = new AccountProcess();
        return accountProcess.process(commands);
    }
}
