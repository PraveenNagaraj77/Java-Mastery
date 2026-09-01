package com.javamastery.oop.FoodDeliverySystem;

public class SMSNotification implements Notification{
    @Override
    public void send(String message) {
        System.out.println("SMS : " + message);
    }
}
