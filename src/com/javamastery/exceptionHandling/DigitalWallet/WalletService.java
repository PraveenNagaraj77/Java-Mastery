package com.javamastery.exceptionHandling.DigitalWallet;

import com.javamastery.exceptionHandling.DigitalWallet.exception.TransactionException;

public class WalletService {
    public void transferMoney(DigitalWallet wallet,double amount) throws TransactionException {
        System.out.println("Transfer Started");
        wallet.withdraw(amount);
        throw new TransactionException("Transaction recording failed");
//        System.out.println("Transfer Completed");
    }
}
