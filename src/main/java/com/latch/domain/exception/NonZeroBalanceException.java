package com.latch.domain.exception;

public class NonZeroBalanceException extends Exception {

    public NonZeroBalanceException(String message) {
        super(message);
    }
}
