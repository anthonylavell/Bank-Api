package com.bank.transaction.domains;

import com.bank.transaction.entites.UsedTxnIdEntity;
import com.bank.transaction.repo.IUsedITxnIdRepository;
import com.bank.transaction.services.TxnService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class AccountProcess {
    @Autowired
    IUsedITxnIdRepository txnRepository;
    private List<String> results;
    private Transaction transaction;
    private TxnService txnService;


    public List<String> process(List<String> commands){
        transaction = new Transaction();
        results = new ArrayList<>();
        txnService = new TxnService();
        for (String command : commands) {

            String[] parts = command.trim().split("\\s+");
            String action = "ERROR";
            if (parts.length > 0) {
                switch (parts[0]) {
                    case "CREATE" -> {
                        if (parts.length == 2) {
                            action = transaction.createAccount(parts[1]);
                        }
                        results.add(action);
                    }
                    case "DEPOSIT" -> {
                        if (parts.length == 4 && txnService.getTxnById(parts[3]) !=null) {
                            action = transaction.depositAccount(parts[0], parts[1], Long.parseLong(parts[2]), parts[3]);
                            if (action.equalsIgnoreCase("OK")) {
                                txnService.save(new UsedTxnIdEntity(parts[3]));
                            }
                            results.add(action);
                        }
                    }
                    case "WITHDRAW" -> {
                        if (parts.length == 4 && txnService.getTxnById(parts[3]) !=null) {
                            action = transaction.withDrawAccount(parts[0], parts[1], Long.parseLong(parts[2]), parts[3]);
                            if (action.equalsIgnoreCase("OK")) {
                                txnService.save(new UsedTxnIdEntity(parts[3]));
                            }
                            results.add(action);
                        }
                    }
                    case "TRANSFER" -> {
                        if (parts.length == 5 && txnService.getTxnById(parts[3]) !=null) {
                            action = transaction.transferAccount(parts[0], parts[1], parts[2], Long.parseLong(parts[3]), parts[4]);
                            if (action.equalsIgnoreCase("OK")) {
                                txnService.save(new UsedTxnIdEntity(parts[4]));
                            }
                            results.add(action);
                        }
                    }
                    case "BALANCE" -> {
                        if (parts.length == 2) {
                            action = transaction.getAccountBalance(parts[1]);
                        }
                        results.add(action);
                    }
                    case "HISTORY" -> {
                        if (parts.length == 3 && Integer.parseInt(parts[2]) > 0) {
                            transaction.getAccountHistory(parts[1], Integer.parseInt(parts[2]), results);
                        } else {
                            results.add(action);
                        }
                    }
                    case "FREEZE", "UNFREEZE" -> {
                        if (parts.length == 2) {
                            action = transaction.setAccountStatus(parts[0], parts[1]);
                        }
                        results.add(action);
                    }
                    default -> results.add("ERROR");
                }
            }
        }
        return results;
    }
}
