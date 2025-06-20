package com.latch.application.dto;

import com.latch.domain.account.model.BankAccount;
import java.util.List;

public record AccountsBalanceOverview(String customerId, List<BankAccount> accounts) {}
