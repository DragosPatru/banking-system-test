package com.latch.domain.account;

import com.latch.domain.account.model.CurrencyUnit;

public interface IbanGenerator {

  String generateIban(CurrencyUnit currencyUnit);
}
