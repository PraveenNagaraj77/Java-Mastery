package com.javamastery.jvmInternals;

public class JVMMemoryPractice {

    static class Product{
        private final String name;

        Product(String name){
            this.name=name;
        }

        String getName(){
            return name;
        }
    }

    static void display(Product product,int count){
        String message = product.getName() + ": " + count;
        System.out.println(message);
    }

    public static void main(String[] args) {
        int count = 10;
        Product product = new Product("Laptop");
        display(product,count);
    }
}
