package com.latch.application;

import com.latch.application.dto.AccountsBalanceOverview;
import com.latch.domain.bank.model.BalanceChangedEvent;

public class BalanceChangedEventHandler {

  private final TaxAuthorityNotifier taxAuthorityNotifier;
  private final BankingService bankingService;

  public BalanceChangedEventHandler(
      TaxAuthorityNotifier taxAuthorityNotifier, BankingService bankingService) {
    this.taxAuthorityNotifier = taxAuthorityNotifier;
    this.bankingService = bankingService;
  }

  public void handle(BalanceChangedEvent event) {
    var customerId = event.customerId();
    if (!bankingService.isBalanceChangeRecording(customerId)) {
      return;
    }

    AccountsBalanceOverview balanceOverview = bankingService.getAccountsBalanceOverview(customerId);
    taxAuthorityNotifier.notifyBalanceChanged(balanceOverview);
  }
}
