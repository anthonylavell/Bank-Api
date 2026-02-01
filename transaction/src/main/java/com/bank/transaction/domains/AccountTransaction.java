package com.bank.transaction.domains;

import com.bank.transaction.entites.AccountEntity;

import com.bank.transaction.services.AccountService;
import com.bank.transaction.services.AccountStatus;



public class AccountTransaction {

    private  final AccountService accountService;
   private AccountEntity account;
    private long balanceCents;
    private AccountStatus status;

    public AccountTransaction(String accountId) {
       accountService = new AccountService();
        this.account = accountService.getAccountById(accountId);
        this.balanceCents = account.getBalance();
        this.status = (account.getStatus().equalsIgnoreCase("ACTIVE")) ?AccountStatus.UNFREEZE
                : AccountStatus.FREEZE;
    }
    public boolean deposit(String type, long amount){
        if (amount <=0 ){
            return false;
        }
        balanceCents+=amount;
        account.setBalanceCents(balanceCents);
        return true;
    }

    public boolean withDraw(String type, long amount){
        if (amount <=0 || balanceCents-amount < 0){
            return false;
        }
        balanceCents-=amount;
        return true;
    }

    public long getBalance(){
        return balanceCents;
    }

    public boolean setStatus(String newStatus){
        status = (newStatus.equalsIgnoreCase("FREEZE")) ? AccountStatus.FREEZE
                : AccountStatus.UNFREEZE;
        return true;
    }

    public String getStatus(){
        return status == AccountStatus.FREEZE ? "FROZEN"
                : "ACTIVE";
    }
}
