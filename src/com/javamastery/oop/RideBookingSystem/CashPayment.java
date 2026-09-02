package com.javamastery.oop.RideBookingSystem;

public class CashPayment implements Payment {
    @Override
    public void pay(double amount) {

        System.out.println(
                "Paid ₹" + amount + " using Cash"
        );
    }
}
