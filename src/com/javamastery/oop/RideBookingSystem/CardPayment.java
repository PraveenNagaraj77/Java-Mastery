package com.javamastery.oop.RideBookingSystem;

public class CardPayment implements Payment{
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + "using Card");
    }
}
