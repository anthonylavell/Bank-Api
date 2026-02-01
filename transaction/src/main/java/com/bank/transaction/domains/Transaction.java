package com.bank.transaction.domains;

import com.bank.transaction.entites.AccountEntity;

import com.bank.transaction.entites.LedgerEntryEntity;
import com.bank.transaction.repo.ILedgerRepository;
import com.bank.transaction.services.AccountService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class Transaction {
    @Autowired
    private ILedgerRepository ledger;

    public String createAccount(String id){
        AccountService accountService = new AccountService();
        if (accountService.getAccountById(id) != null) {
            return "ERROR";
        }
        accountService.save(new AccountEntity(id));
        return "OK";
    }
    public String getAccountBalance(String id){
        AccountService accountService = new AccountService();
        AccountEntity account = accountService.getAccountById(id);
        if (account == null) {
            return "ERROR";
        }
        return String.valueOf(account.getBalance());
    }
    public void getAccountHistory(String id, int limit, List<String> results){
        AccountService accountService = new AccountService();
        AccountEntity account = accountService.getAccountById(id);
        if (account == null) {
            results.add("ERROR");
            return;
        }
        Pageable pageable = PageRequest.of(0, limit);
        List<LedgerEntryEntity> history = ledger.findByAccountIdOrderByCreatedAtDesc(id,pageable);
        //for (int index = history.size()-1; index >=0 && limit-->0; index--){
            List<String> accountHistory = history.stream()
                    .map(LedgerEntryEntity::getEntryText)
                    .toList();
            results.addAll(accountHistory);
        //}

    }
    @Transactional
    public String setAccountStatus(String type,String id){
        AccountService accountService = new AccountService();
        AccountEntity account = accountService.getAccountById(id);
        if (account == null) {
            return "ERROR";
        }

        account.setStatus(type);
        return "OK";
    }
    @Transactional
    public String depositAccount(String type, String id, long amount, String taxId){
        AccountService accountService = new AccountService();
        AccountEntity account = accountService.getAccountById(id);
        if (account == null) {
            return "ERROR";
        }
        long currentBal = account.getBalance();
        if (account.getStatus().equalsIgnoreCase("FROZEN")|| amount < 1) {
            return "ERROR";
        }

        account.setBalanceCents(amount-currentBal);
        setHistory(id,type,amount,taxId);
        accountService.save(account);
        return "OK";
    }
    @Transactional
    public String withDrawAccount(String type, String id, long amount, String taxId){
        AccountService accountService = new AccountService();
        AccountEntity account = accountService.getAccountById(id);
        if (account == null) {
            return "ERROR";
        }
        long currentBal = account.getBalance();
        if (account.getStatus().equalsIgnoreCase("FROZEN")|| currentBal < 1 || amount < 1) {
            return "ERROR";
        }
        account.setBalanceCents(amount-currentBal);
        setHistory(id,type,amount,taxId);
        accountService.save(account);
        return "OK";
    }

    @Transactional
    public String transferAccount(String type, String firstId,String secondId, long amount, String taxId){
        AccountService accountService = new AccountService();
        AccountEntity fromAccount = accountService.getAccountById(firstId);
        AccountEntity toAccount = accountService.getAccountById(secondId);
        if (fromAccount == null || toAccount == null) {
            return "ERROR";
        }


        String transTypeOut = type+"_OUT "+secondId;
        String transTypeIn = type+"_IN "+firstId;
        if (fromAccount.getStatus().equalsIgnoreCase("FROZEN")
                || toAccount.getStatus().equalsIgnoreCase("FROZEN")
                ||  withDrawAccount(transTypeOut,firstId,amount,taxId).equals("ERROR")
                ||  depositAccount(transTypeIn,secondId,amount,taxId).equals("ERROR")){
            return "ERROR";
        }
        return "OK";
    }

    private void setHistory(String accountId,String type, long amount,String txnId){
        String transaction = type+" "+amount+" "+txnId;
        ledger.save(new LedgerEntryEntity(accountId,transaction));
    }
}
