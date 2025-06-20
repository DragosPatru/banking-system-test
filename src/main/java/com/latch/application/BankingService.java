package com.latch.application;

import com.latch.application.dto.AccountsBalanceOverview;
import com.latch.domain.bank.model.BankAccount;

import java.math.BigDecimal;
import java.util.List;

public interface BankingService {

  List<BankAccount> createAccounts(String customerId);

  void closeAccounts(String customerId);

  AccountsBalanceOverview getAccountsBalanceOverview(String customerId);

  void deposit(String customerId, BigDecimal amount, String toAccountIban);

  void withdraw(String customerId, BigDecimal amount, String fromAccountIban);

  void startBalanceChangeRecording(String customerId);

  void stopBalanceChangeRecording(String customerId);

  boolean isBalanceChangeRecording(String customerId);
}
