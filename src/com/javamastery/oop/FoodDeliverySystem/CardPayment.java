package com.javamastery.oop.FoodDeliverySystem;

public class CardPayment extends Payment {
    private String cardNumber;

    CardPayment(double amount, String cardNumber){
        super(amount);
        this.cardNumber = cardNumber;
    }

    @Override
    void processPayment() {

        System.out.println(
                "Processing Card payment..."
        );

        System.out.println(
                "Card ending: ****"
                        + cardNumber.substring(
                        cardNumber.length() - 4
                )
        );

        System.out.println(
                "Amount: ₹" + amount
        );
    }
}
