package com.latch.domain.exception;

public class AccountsAlreadyCreatedException extends RuntimeException {

  public AccountsAlreadyCreatedException(String message) {
    super(message);
  }
}
