package com.javamastery.java8.defaultandstaticinterfacemethods;

public interface Logger {
    default void log(){
        System.out.println("Default Logging");
    }
}
