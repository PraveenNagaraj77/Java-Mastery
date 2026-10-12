package com.javamastery.jvmInternals;

public class GCExample {

    static class Product{
        String name;
        Product(String name){
            this.name=name;
        }
    }

    public static void main(String[] args) {
        Product first = new Product("Laptop");
        Product second = new Product("Phone");

        first = null;
        second = new Product("Tablet");

        System.out.println(second.name);
    }
}
