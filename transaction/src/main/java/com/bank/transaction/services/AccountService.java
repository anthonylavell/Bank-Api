package com.bank.transaction.services;

import com.bank.transaction.entites.AccountEntity;
import com.bank.transaction.repo.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    IAccountRepository accountRepo;

    public void save(AccountEntity accountEntity){
        accountRepo.save(accountEntity);
    }

    public AccountEntity getAccountById(String id){
        return accountRepo.findById(id).isEmpty()?null:accountRepo.findById(id).get();
    }

}
