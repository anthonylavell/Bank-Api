package com.bank.transaction.apis;

import com.bank.transaction.DTOs.BankCommandRequest;
import com.bank.transaction.DTOs.BankCommandResponse;
import com.bank.transaction.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank")
public class TransactionApiController {
    private final BankService bankService;

    public TransactionApiController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/process")
    public BankCommandResponse process(@RequestBody BankCommandRequest request) {
        if (request == null || request.commands() == null) {
            throw new IllegalArgumentException("commands is required");
        }
        return new BankCommandResponse( bankService.process(request.commands()) );
    }
}
