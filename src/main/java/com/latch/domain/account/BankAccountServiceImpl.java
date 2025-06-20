package com.latch.domain.account;

import static com.latch.domain.account.model.CurrencyUnit.*;

import com.latch.domain.account.model.BalanceChangedEvent;
import com.latch.domain.account.model.BankAccount;
import com.latch.domain.event.DomainEventPublisher;
import com.latch.domain.exception.AccountsAlreadyCreatedException;
import com.latch.domain.exception.NonZeroBalanceException;
import com.latch.domain.exception.ResourceNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class BankAccountServiceImpl implements BankAccountService {

  private final BankAccountRepository repository;
  private final IbanGenerator ibanGenerator;
  private final DomainEventPublisher eventPublisher;

  public BankAccountServiceImpl(
      BankAccountRepository repository,
      IbanGenerator ibanGenerator,
      DomainEventPublisher eventPublisher) {
    this.repository = repository;
    this.ibanGenerator = ibanGenerator;
    this.eventPublisher = eventPublisher;
  }

  /** must be @Transactional */
  @Override
  public List<BankAccount> createAccounts(String customerId) {
    if (!getAccounts(customerId).isEmpty()) {
      throw new AccountsAlreadyCreatedException(customerId);
    }
    BankAccount ronAccount =
        new BankAccount(ibanGenerator.generateIban(RON), RON, customerId, BigDecimal.ZERO);
    BankAccount eurAccount =
        new BankAccount(ibanGenerator.generateIban(EURO), EURO, customerId, BigDecimal.ZERO);

    return repository.saveAll(List.of(ronAccount, eurAccount));
  }

  /** must be @Transactional */
  @Override
  public void closeAccounts(String customerId) throws NonZeroBalanceException {
    List<BankAccount> accounts = getAccounts(customerId);
    for (var account : accounts) {
      if (!BigDecimal.ZERO.equals(account.getBalance())) {
        throw new NonZeroBalanceException(account.getIban());
      }
    }
    repository.deleteAllByCustomerId(customerId);
  }

  @Override
  public List<BankAccount> getAccounts(String customerId) {
    return repository.findByCustomerId(customerId);
  }

  /** must be @Transactional */
  @Override
  public BankAccount deposit(String customerId, BigDecimal amount, String toAccountIban) {
    BankAccount bankAccount =
        repository.findBy(toAccountIban, customerId).orElseThrow(ResourceNotFoundException::new);
    // TODO: apply business rules for balance & throw checked exception if does not comply
    BigDecimal oldBalance = bankAccount.getBalance();
    BigDecimal newBalance = oldBalance.add(amount);
    bankAccount.setBalance(newBalance);
    bankAccount = repository.save(bankAccount);

    if (!Objects.equals(BigDecimal.ZERO, amount)) {
      eventPublisher.publish(
          new BalanceChangedEvent(
              bankAccount.getIban(),
              bankAccount.getCurrency(),
              oldBalance,
              newBalance,
              customerId));
    }
    return bankAccount;
  }

  /** must be @Transactional */
  @Override
  public BankAccount withdraw(String customerId, BigDecimal amount, String fromAccountIban) {
    BankAccount bankAccount =
        repository.findBy(fromAccountIban, customerId).orElseThrow(ResourceNotFoundException::new);
    // TODO: apply business rules for balance & throw checked exception if does not comply
    BigDecimal newBalance = bankAccount.getBalance().subtract(amount);
    BigDecimal oldBalance = bankAccount.getBalance();
    bankAccount.setBalance(newBalance);
    bankAccount = repository.save(bankAccount);

    if (!Objects.equals(BigDecimal.ZERO, amount)) {
      eventPublisher.publish(
          new BalanceChangedEvent(
              bankAccount.getIban(),
              bankAccount.getCurrency(),
              oldBalance,
              newBalance,
              customerId));
    }
    return bankAccount;
  }
}
