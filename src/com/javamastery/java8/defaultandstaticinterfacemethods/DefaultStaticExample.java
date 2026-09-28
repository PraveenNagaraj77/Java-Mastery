package com.javamastery.java8.defaultandstaticinterfacemethods;

public class DefaultStaticExample implements Logger,A,B {

    @Override
    public void log() {
        System.out.println("Application Logging");
    }

    @Override
    public void show() {
        B.super.show();
    }

    public static void main(String[] args) {
        DefaultStaticExample example = new DefaultStaticExample();

        example.log();
        example.show();

        A.validate();


    }

}
