package com.latch.application.dto;

import com.latch.domain.bank.model.BankAccount;

import java.util.List;

public record AccountsBalanceOverview(String customerId, List<BankAccount> accounts) {}
