package com.latch.domain.account;

import com.latch.domain.account.model.BankAccount;

import java.math.BigDecimal;
import java.util.List;

public interface BankAccountService {

  List<BankAccount> createAccounts(String customerId);

  void closeAccounts(String customerId);

  List<BankAccount> getAccounts(String customerId);

  void deposit(String customerId, BigDecimal amount, String toAccountIban);

  void withdraw(String customerId, BigDecimal amount, String fromAccountIban);
}
