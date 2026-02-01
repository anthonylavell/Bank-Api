package com.bank.transaction.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "used_txn_ids")
@Getter
@NoArgsConstructor
public class UsedTxnIdEntity {
    @Id
    @Column(name = "txn_id")
    private String txnId;
    public UsedTxnIdEntity(String txnId){
        this.txnId = txnId;
    }
}
