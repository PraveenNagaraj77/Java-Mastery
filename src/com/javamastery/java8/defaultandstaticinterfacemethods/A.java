package com.javamastery.java8.defaultandstaticinterfacemethods;

public interface A {
    default void show(){
        System.out.println("A");
    }

    static void validate(){
        System.out.println("Validation from A");
    }
}
