package com.bank.transaction.services;

import lombok.Getter;
import org.springframework.stereotype.Service;


public enum AccountStatus {
    UNFREEZE("ACTIVE"),
    FREEZE("FROZEN");

    @Getter
    final String status;
    AccountStatus(String status){
        this.status = status;
    }

}
