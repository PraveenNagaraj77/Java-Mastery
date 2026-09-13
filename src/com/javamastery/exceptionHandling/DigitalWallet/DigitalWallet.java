package com.javamastery.exceptionHandling.DigitalWallet;

import com.javamastery.exceptionHandling.DigitalWallet.exception.InsufficientBalanceException;
import com.javamastery.exceptionHandling.DigitalWallet.exception.InvalidAmountException;

public class DigitalWallet {

    private String userName;
    private double balance;

    public DigitalWallet(String userName, double balance) {
        this.userName = userName;
        this.balance = balance;
    }

    public String getUserName() {
        return userName;
    }

    public double getBalance() {
        return balance;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double deposit(double amount) {
        if(amount<=0){
            throw new InvalidAmountException("Invalid Amount");
        }
        return balance += amount;
    }

    public double withdraw(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Invalid Amount");
        }

        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient Balance");
        }

        balance -= amount;
        return balance;
    }
}