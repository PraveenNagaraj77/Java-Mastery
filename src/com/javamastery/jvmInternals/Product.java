package com.javamastery.jvmInternals;

public class Product {
    static int count = 10;

    static {
        System.out.println("Product Class Initialized");
    }

    public Product(){
        System.out.println("Product Object Created");
    }

    public static void main(String[] args) {
        System.out.println(Product.count);
        Product p = new Product();
    }
}
