package com.bank.transaction.services;

import com.bank.transaction.entites.AccountEntity;
import com.bank.transaction.repo.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final IAccountRepository accountRepo;

    public AccountService(IAccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    public void save(AccountEntity accountEntity){
        accountRepo.save(accountEntity);
    }

    public AccountEntity getAccountById(String id){
        return accountRepo.findById(id).orElse(null);
    }

    public boolean exists(String id){
        return accountRepo.existsById(id);
    }

}
