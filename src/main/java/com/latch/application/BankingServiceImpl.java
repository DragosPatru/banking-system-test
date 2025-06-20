package com.latch.application;

import com.latch.application.dto.AccountsBalanceOverview;
import com.latch.domain.account.BankAccountService;
import com.latch.domain.account.model.BankAccount;
import com.latch.domain.customer.CustomerService;
import com.latch.domain.customer.model.Customer;
import com.latch.domain.exception.NonZeroBalanceException;
import com.latch.domain.financialmonitoring.FinancialMonitoringService;
import java.math.BigDecimal;
import java.util.List;

public class BankingServiceImpl implements BankingService {

  private final BankAccountService bankAccountService;
  private final CustomerService customerService;
  private final FinancialMonitoringService financialMonitoringService;

  public BankingServiceImpl(
      BankAccountService bankAccountService,
      CustomerService customerService,
      FinancialMonitoringService financialMonitoringService) {
    this.bankAccountService = bankAccountService;
    this.customerService = customerService;
    this.financialMonitoringService = financialMonitoringService;
  }

  /** must be @Transactional */
  @Override
  public List<BankAccount> registerCustomer(Customer customer) {
    customerService.createCustomer(customer);
    return bankAccountService.createAccounts(customer.getId());
  }

  /**
   * must be @Transactional
   *
   * <p>in 'REAL life' it can be better handled with a notification on a topic and multiple services
   * listening to it in order to delete the data from multiple places
   */
  @Override
  public void unregisterCustomer(String customerId) throws NonZeroBalanceException {
    customerService.deleteCustomer(customerId);
    bankAccountService.closeAccounts(customerId);
    financialMonitoringService.stopMonitoring(customerId);
  }

  @Override
  public AccountsBalanceOverview getAccountsBalanceOverview(String customerId) {
    return new AccountsBalanceOverview(customerId, bankAccountService.getAccounts(customerId));
  }

  @Override
  public BankAccount deposit(String customerId, BigDecimal amount, String toAccountIban) {
    return bankAccountService.deposit(customerId, amount, toAccountIban);
  }

  @Override
  public BankAccount withdraw(String customerId, BigDecimal amount, String fromAccountIban) {
    return bankAccountService.withdraw(customerId, amount, fromAccountIban);
  }

  @Override
  public void startBalanceChangeRecording(String customerId) {
    financialMonitoringService.startMonitoring(customerId);
  }

  @Override
  public void stopBalanceChangeRecording(String customerId) {
    financialMonitoringService.stopMonitoring(customerId);
  }

  @Override
  public boolean isBalanceChangeRecording(String customerId) {
    return financialMonitoringService.isMonitored(customerId);
  }
}
