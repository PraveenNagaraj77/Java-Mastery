package com.javamastery.oop.FoodDeliverySystem;

abstract public class Payment {
    protected double amount;

    Payment(double amount){
        this.amount=amount;
    }

    abstract void processPayment();

}
