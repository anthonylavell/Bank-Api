package com.bank.transaction.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "ledger_entries")
@Getter
@NoArgsConstructor
public class LedgerEntryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id", nullable = false)
    private String accountId;

    @Column(name = "entry_text", nullable = false)
    private String entryText;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    public LedgerEntryEntity(String accountId, String entryText) {
        this.accountId = accountId;
        this.entryText = entryText;
        this.createdAt = Instant.now();
    }
}
