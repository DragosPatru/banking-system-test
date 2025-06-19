package com.latch.domain.service;

import com.latch.domain.model.BalanceChangedEvent;

public interface BalanceChangedListener {

  void onBalanceChanged(BalanceChangedEvent event);
}
