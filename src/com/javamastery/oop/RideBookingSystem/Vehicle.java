package com.javamastery.oop.RideBookingSystem;

public class Vehicle {
    protected String vehicleNumber;
    protected String brand;

    public Vehicle(String vehicleNumber, String brand) {
        this.vehicleNumber = vehicleNumber;
        this.brand = brand;
    }

    public void displayVehicle() {

        System.out.println(
                "Vehicle Number : " + vehicleNumber
        );

        System.out.println(
                "Brand          : " + brand
        );
    }

    public double calculateFare(double distance){
        return distance * 15;
    }
}
