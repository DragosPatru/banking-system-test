package com.latch.domain.service;

import com.latch.domain.model.BankAccount;

public interface BalanceChangedNotifier {

  boolean notifyBalanceChanged(BankAccount account);
}
