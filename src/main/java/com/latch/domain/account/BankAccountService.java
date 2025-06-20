package com.latch.domain.account;

import com.latch.domain.account.model.BankAccount;
import com.latch.domain.exception.NonZeroBalanceException;

import java.math.BigDecimal;
import java.util.List;

public interface BankAccountService {

  List<BankAccount> createAccounts(String customerId);

  void closeAccounts(String customerId) throws NonZeroBalanceException;

  List<BankAccount> getAccounts(String customerId);

  BankAccount deposit(String customerId, BigDecimal amount, String toAccountIban);

  BankAccount withdraw(String customerId, BigDecimal amount, String fromAccountIban);
}
