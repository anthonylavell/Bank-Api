package com.bank.transaction.services;

import com.bank.transaction.entites.UsedTxnIdEntity;
import com.bank.transaction.repo.IUsedITxnIdRepository;

import org.springframework.stereotype.Service;

@Service
public class TxnService {
    private final IUsedITxnIdRepository txnRepository;

    public TxnService(IUsedITxnIdRepository txnRepository) {
        this.txnRepository = txnRepository;
    }

    public UsedTxnIdEntity getTxnById(String id){
        return txnRepository.findById(id).orElse(null);
    }

    public void save(UsedTxnIdEntity e) {
        txnRepository.save(e);
    }

    public boolean doExists(String id){
        return txnRepository.existsById(id);
    }
}
