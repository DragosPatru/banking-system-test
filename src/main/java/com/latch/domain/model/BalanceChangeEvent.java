package com.latch.domain.model;

import java.math.BigDecimal;

public record BalanceChangeEvent(
    String accountId,
    CurrencyUnit currency,
    BigDecimal oldBalance,
    BigDecimal newBalance,
    String customerId) {}
