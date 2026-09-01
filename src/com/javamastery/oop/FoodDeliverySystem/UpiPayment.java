package com.javamastery.oop.FoodDeliverySystem;

public class UpiPayment extends Payment {
    private String UPIid;

    UpiPayment(double amount,String UPIid){
        super(amount);
        this.UPIid =UPIid;
    }

    @Override
    void processPayment() {
        System.out.println("Processing UPI Payment");
        System.out.println(
                "UPI ID: " + UPIid
        );

        System.out.println(
                "Amount: ₹" + amount
        );
    };


}
