package com.bank.transaction.repo;

import com.bank.transaction.entites.LedgerEntryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILedgerRepository extends JpaRepository<LedgerEntryEntity, Long> {
    List<LedgerEntryEntity> findByAccountIdOrderByCreatedAtDesc(String accountId, Pageable pageable);
}
