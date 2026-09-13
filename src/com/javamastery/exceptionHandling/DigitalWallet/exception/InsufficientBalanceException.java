package com.javamastery.exceptionHandling.DigitalWallet.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message){
        super(message);
    }
}
