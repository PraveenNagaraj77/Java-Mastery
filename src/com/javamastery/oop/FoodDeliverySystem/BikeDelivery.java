package com.javamastery.oop.FoodDeliverySystem;

public class BikeDelivery implements DeliveryService {
    @Override
    public void deliver(String address) {
        System.out.println("Bike delivery to: "
                + address
        );
    }
}
