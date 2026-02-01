package com.bank.transaction.services;

import com.bank.transaction.entites.UsedTxnIdEntity;
import com.bank.transaction.repo.IUsedITxnIdRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TxnService {
    @Autowired
    IUsedITxnIdRepository txnRepo;

    public void save(UsedTxnIdEntity usedTxnIdEntity){
        txnRepo.save(usedTxnIdEntity);
    }

    public UsedTxnIdEntity getTxnById(String id){
        return txnRepo.findById(id).isEmpty()?null: txnRepo.findById(id).get();
    }
}
