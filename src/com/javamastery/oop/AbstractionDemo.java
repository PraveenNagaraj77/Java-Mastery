package com.javamastery.oop;

public class AbstractionDemo {
    static abstract  class Payment{
        protected double amount;

        Payment(double amount){
            this.amount = amount;
        }

        //abstract method
        abstract void pay();

        void printReceipt(){
            System.out.println("Payment Successfull : "+amount);
        }

    }

    static class UPIPayment extends Payment{
        private String upiId;
        UPIPayment(double amount , String upiId){
            super(amount);
            this.upiId = upiId;
        }


        void pay(){
            System.out.println("Processing UPI Payment");
            System.out.println("UPI Id : " + upiId);
            System.out.println("Amount : "+ amount);
        }

    }

    static  class CardPayment extends Payment{
        private String cardNumber;

        CardPayment(double amount,String cardNumber){
            super(amount);
            this.cardNumber = cardNumber;
        }

        void pay(){
            System.out.println(
                    "Processing card payment..."
            );

            System.out.println(
                    "Card: ****" +
                            cardNumber.substring(
                                    cardNumber.length() - 4
                            )
            );

            System.out.println(
                    "Amount: ₹" + amount
            );
        }

    }

    public static void main(String[] args) {
        Payment payment;

        payment = new UPIPayment(1499,"praveen@upi");
        payment.pay();
        payment.printReceipt();

        System.out.println();


        payment = new CardPayment(
                2999,
                "1234567812345678"
        );

        payment.pay();
        payment.printReceipt();

    }



}
