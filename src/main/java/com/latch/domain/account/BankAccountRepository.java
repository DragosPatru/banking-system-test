package com.latch.domain.account;

import com.latch.domain.account.model.BankAccount;
import java.util.List;
import java.util.Optional;

public interface BankAccountRepository {

  Optional<BankAccount> findBy(String iban, String customerId);

  List<BankAccount> findByCustomerId(String customerId);

  /** add or update */
  BankAccount save(BankAccount bankAccount);

  List<BankAccount> saveAll(List<BankAccount> bankAccounts);

  void deleteAllByCustomerId(String customerId);
}
