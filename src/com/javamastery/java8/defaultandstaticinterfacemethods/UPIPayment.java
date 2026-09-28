package com.javamastery.java8.defaultandstaticinterfacemethods;

public class UPIPayment implements PaymentService {
    @Override
    public void pay(double amount) {
        if(!PaymentService.isValidAmount(amount)){
            System.out.println("Invalid payment amount");
            return;
        }

        System.out.println("Processing UPI Payment : " + amount);
        logTransaction(amount);
    }

    @Override
    public void logTransaction(double amount) {
        System.out.println("UPI Transaction logged : " +amount);
    }
}
