package com.javamastery.oop.RideBookingSystem;

public class Auto extends Vehicle{
    public Auto(String vehicleNumber,String brand){
        super(vehicleNumber,brand);
    }

    @Override
    public double calculateFare(double distance) {
        return distance * 12;
    }
}
