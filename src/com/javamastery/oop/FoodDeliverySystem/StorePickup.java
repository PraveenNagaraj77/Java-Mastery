package com.javamastery.oop.FoodDeliverySystem;

public class StorePickup implements DeliveryService{
    @Override
    public void deliver(String address) {
        System.out.println(
                "Customer pickup from restaurant"
        );
    }
}
