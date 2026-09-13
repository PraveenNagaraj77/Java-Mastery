package com.javamastery.generics;

public class GenericClassExample {
    public static void main(String[] args) {
        Box<String> box1 = new Box<>();
        Box<Integer> box2 = new Box<>();
        box1.setValue("Laptop");
        box2.setValue(100);
        System.out.println(box1.getValue());
        System.out.println(box2.getValue());
    }
}
