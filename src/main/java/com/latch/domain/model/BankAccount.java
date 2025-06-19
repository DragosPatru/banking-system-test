package com.latch.domain.model;

import java.math.BigDecimal;

public class BankAccount {

  private final String iban;
  private final CurrencyUnit currency;
  private BigDecimal balance;
  private final String customerId;

  public BankAccount(String iban, CurrencyUnit currency, String customerId) {
    this.iban = iban;
    this.currency = currency;
    this.customerId = customerId;
  }

  public BigDecimal getBalance() {
    return this.balance;
  }

  public void setBalance(BigDecimal newBalance) {
    this.balance = newBalance;
  }

  public String getIban() {
    return iban;
  }

  public CurrencyUnit getCurrency() {
    return currency;
  }

  public String getCustomerId() {
    return customerId;
  }
}
