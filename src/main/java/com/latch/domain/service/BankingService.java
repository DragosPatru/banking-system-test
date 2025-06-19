package com.latch.domain.service;

import com.latch.domain.model.BankAccount;
import java.math.BigDecimal;
import java.util.List;

public interface BankingService {

  List<BankAccount> createAccounts(String customerId);

  void closeAccounts(String customerId);

  void accountsDetails(String customerId);

  void deposit(String customerId, BigDecimal amount, String toAccountIban);

  void withdraw(String customerId, BigDecimal amount, String fromAccountIban);

  void startBalanceChangeRecording(String customerId);
}
