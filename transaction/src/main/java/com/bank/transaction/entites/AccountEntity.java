package com.bank.transaction.entites;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
public class AccountEntity {
    @Id
    @Column(name = "account_id")
    private String id;
    @Column(name = "balance_cents")
    private long balanceCents;
    private String status; /// "ACTIVE" or "FROZEN"
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt = Instant.now();


    public AccountEntity(String id){
        this.id = id;
        balanceCents = 0L;
        status = "ACTIVE";
        this.updatedAt = Instant.now();
    }

    @PrePersist
    public void prePersist() { this.updatedAt = Instant.now(); }

    @PreUpdate
    public void preUpdate() { this.updatedAt = Instant.now(); }

    public void setBalanceCents(long balanceCents) {
        this.balanceCents = balanceCents;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getBalance() {
        return balanceCents;
    }

    public String getStatus() {
        return status;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
