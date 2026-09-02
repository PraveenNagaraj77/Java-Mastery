package com.javamastery.oop.RideBookingSystem;

public class EmailNotification implements  Notification {
    @Override
    public void send(String message) {
        System.out.println("Email Notification " +message);
    }
}

