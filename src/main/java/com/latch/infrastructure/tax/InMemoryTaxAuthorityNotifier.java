package com.latch.infrastructure.tax;

import com.latch.application.TaxAuthorityNotifier;
import com.latch.application.dto.AccountsBalanceOverview;
import com.latch.domain.bank.model.BankAccount;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * TaxAuthority is normally an external service which has it's own storage and the internal Cache
 * can be a distributed one like Redis
 */
public class InMemoryTaxAuthorityNotifier implements TaxAuthorityNotifier {

  private final Map<String, AccountsBalanceOverview> cache = new HashMap<>();

  @Override
  public void notifyBalanceChanged(AccountsBalanceOverview currentOverview) {
    var customerId = currentOverview.customerId();
    AccountsBalanceOverview previousOverview = cache.put(customerId, currentOverview);
    printBalanceChangedComparing(previousOverview, currentOverview);
  }

  private void printBalanceChangedComparing(
      AccountsBalanceOverview previous, AccountsBalanceOverview current) {
    if (previous == null) {
      for (var account : current.accounts()) {
        printBalanceChanged(account);
      }

    } else {
      Map<String, BankAccount> previousAccounts =
          previous.accounts().stream()
              .collect(Collectors.toMap(BankAccount::getIban, Function.identity()));
      for (BankAccount currentAccount : current.accounts()) {
        BankAccount previousAccount = previousAccounts.get(currentAccount.getIban());
        if (previousAccount == null) {
          continue;
        }

        if (checkBalanceChanged(previousAccount, currentAccount)) {
          printBalanceChanged(currentAccount);
        }
      }
    }
  }

  private boolean checkBalanceChanged(BankAccount previous, BankAccount current) {
    if (!previous.getIban().equals(current.getIban())) {
      throw new IllegalArgumentException("IBANs do not match.");
    }

    BigDecimal oldBalance = previous.getBalance();
    BigDecimal newBalance = current.getBalance();
    return !Objects.equals(oldBalance, newBalance);
  }

  private void printBalanceChanged(BankAccount bankAccount) {
    System.out.println("Account " + bankAccount.getIban() + ": balance changed");
  }
}
