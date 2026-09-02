package com.javamastery.oop.RideBookingSystem;

public class UpiPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid "+ amount + "Using UPI");
    }
}


