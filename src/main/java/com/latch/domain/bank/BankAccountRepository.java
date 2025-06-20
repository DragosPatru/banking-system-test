package com.latch.domain.bank;

import com.latch.domain.bank.model.BankAccount;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository {

  Optional<BankAccount> findById(String iban);

  List<BankAccount> findByCustomerId(String customerId);

  /** add or update */
  BankAccount save(BankAccount bankAccount);

  void deleteAllByCustomerId(String customerId);
}
