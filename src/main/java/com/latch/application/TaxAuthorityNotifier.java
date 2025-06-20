package com.latch.application;

import com.latch.application.dto.AccountsBalanceOverview;

public interface TaxAuthorityNotifier {

  void notifyBalanceChanged(AccountsBalanceOverview overview);
}
