package com.javamastery.exceptionHandling.DigitalWallet.exception;

public class InvalidAmountException extends RuntimeException{
    public InvalidAmountException(String message){
        super(message);
    }
}
