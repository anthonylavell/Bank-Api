package com.bank.transaction.repo;

import com.bank.transaction.entites.UsedTxnIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsedITxnIdRepository extends JpaRepository<UsedTxnIdEntity,String> {
}
