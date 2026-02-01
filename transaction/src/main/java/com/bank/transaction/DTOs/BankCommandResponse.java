package com.bank.transaction.DTOs;

import java.util.List;

public record BankCommandResponse(List<String> outputs) {}
