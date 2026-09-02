package com.javamastery.oop.RideBookingSystem;

public class Car extends Vehicle {
    private int seats;
    public Car(
            String vehicleNumber,
            String brand,
            int seats
    ) {

        super(vehicleNumber, brand);

        this.seats = seats;
    }

    public double calculateFare(double distance){
        return distance * 20;
    }

    public void displayVehicle(){
        super.displayVehicle();
        System.out.println("Seats          : "+seats);
    }



}
