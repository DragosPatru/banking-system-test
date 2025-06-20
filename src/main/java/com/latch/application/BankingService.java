package com.latch.application;

import com.latch.application.dto.AccountsBalanceOverview;
import com.latch.domain.account.model.BankAccount;
import com.latch.domain.customer.model.Customer;
import com.latch.domain.exception.NonZeroBalanceException;
import java.math.BigDecimal;
import java.util.List;

public interface BankingService {

  List<BankAccount> registerCustomer(Customer customer);

  void unregisterCustomer(String customerId) throws NonZeroBalanceException;

  AccountsBalanceOverview getAccountsBalanceOverview(String customerId);

  BankAccount deposit(String customerId, BigDecimal amount, String toAccountIban);

  BankAccount withdraw(String customerId, BigDecimal amount, String fromAccountIban);

  void startBalanceChangeRecording(String customerId);

  void stopBalanceChangeRecording(String customerId);

  boolean isBalanceChangeRecording(String customerId);
}
