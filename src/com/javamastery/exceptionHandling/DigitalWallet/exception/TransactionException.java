package com.javamastery.exceptionHandling.DigitalWallet.exception;

public class TransactionException extends  Exception{
    public TransactionException(String message){
        super(message);
    }
}
