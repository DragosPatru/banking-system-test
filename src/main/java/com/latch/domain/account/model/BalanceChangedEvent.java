package com.latch.domain.account.model;

import com.latch.domain.event.DomainEvent;
import java.math.BigDecimal;

public record BalanceChangedEvent(
    String accountId,
    CurrencyUnit currency,
    BigDecimal oldBalance,
    BigDecimal newBalance,
    String customerId)
    implements DomainEvent {}
