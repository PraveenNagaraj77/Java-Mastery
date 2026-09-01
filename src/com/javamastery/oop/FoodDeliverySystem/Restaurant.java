package com.javamastery.oop.FoodDeliverySystem;

public class Restaurant {
    private int restaurantId;
    private String name;

    Restaurant(int restaurantId,String name){
        this.restaurantId = restaurantId;
        this.name = name;
    }

    public String getName(){
        return  name;
    }

}
