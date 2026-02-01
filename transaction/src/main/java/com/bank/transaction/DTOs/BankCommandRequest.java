package com.bank.transaction.DTOs;

import java.util.List;

public record BankCommandRequest(List<String> commands) {}
