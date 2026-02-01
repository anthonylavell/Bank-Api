create table if not exists accounts (
                                        account_id text primary key,
                                        balance_cents bigint not null,
                                        status text not null,
                                        updated_at timestamptz not null default now()
    );

create index if not exists idx_accounts_status on accounts(status);

create table if not exists used_txn_ids (
                                            txn_id text primary key,
                                            created_at timestamptz not null default now()
    );

create table if not exists ledger_entries (
                                              id bigserial primary key,
                                              account_id text not null references accounts(account_id),
    entry_text text not null,
    created_at timestamptz not null default now()
    );

create index if not exists idx_ledger_account_time
    on ledger_entries(account_id, created_at desc);
