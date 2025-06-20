package com.latch.infrastructure.event;

import com.latch.application.BalanceChangedEventHandler;
import com.latch.domain.account.model.BalanceChangedEvent;

/**
 * in case of outbox pattern - a thread will read the events table in case of Kafka - Spring
 * provides annotations in case of Spring Event Listener - it provides annotations
 */
public class BalanceChangedEventListener {

  private final BalanceChangedEventHandler eventHandler;

  public BalanceChangedEventListener(BalanceChangedEventHandler eventHandler) {
    this.eventHandler = eventHandler;
  }

  public void onEvent(BalanceChangedEvent event) {
    eventHandler.handle(event);
  }
}
