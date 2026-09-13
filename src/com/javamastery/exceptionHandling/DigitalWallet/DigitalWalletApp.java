package com.javamastery.exceptionHandling.DigitalWallet;

import com.javamastery.exceptionHandling.DigitalWallet.exception.InsufficientBalanceException;
import com.javamastery.exceptionHandling.DigitalWallet.exception.InvalidAmountException;
import com.javamastery.exceptionHandling.DigitalWallet.exception.TransactionException;

public class DigitalWalletApp {
    public static void main(String[] args) {
        DigitalWallet wallet = new DigitalWallet("Praveen",10000);

        System.out.println(wallet.getBalance());
        wallet.deposit(1000);
        wallet.withdraw(2000);
        System.out.println(wallet.getBalance());

        WalletService service = new WalletService();

        try{
            wallet.withdraw(-500);
        }catch (InsufficientBalanceException | InvalidAmountException e){
            System.out.println(e.getMessage());
        }finally {
            System.out.println("Transaction attempt completed");
        }


        try{
            service.transferMoney(wallet,7000);
        }catch (TransactionException e){
            System.out.println(e.getMessage());
        }



        System.out.println("Transaction processing completed");


    }
}
