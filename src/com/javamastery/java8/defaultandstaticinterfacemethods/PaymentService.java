package com.javamastery.java8.defaultandstaticinterfacemethods;

public interface PaymentService {

    //abstract method
    void pay(double amount);

    //default method

    default void logTransaction(double amount){
        System.out.println("Transaction Logged : " + amount);
    }

    //static method

    static boolean isValidAmount(double amount){
        return amount>0;
    }

}
