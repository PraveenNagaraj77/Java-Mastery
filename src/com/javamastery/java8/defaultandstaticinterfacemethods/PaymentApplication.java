package com.javamastery.java8.defaultandstaticinterfacemethods;

public class PaymentApplication {
    public static void main(String[] args) {
        PaymentService payment = new UPIPayment();

        payment.pay(1500);

        System.out.println();

        payment.pay(-500);

        System.out.println();

        System.out.println("Valid Amount : " + PaymentService.isValidAmount(2500));
    }
}
