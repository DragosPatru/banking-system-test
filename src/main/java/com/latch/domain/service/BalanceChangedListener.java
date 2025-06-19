package com.latch.domain.service;

import com.latch.domain.model.BalanceChangeEvent;

public interface BalanceChangedListener {

  void onBalanceChanged(BalanceChangeEvent event);
}
