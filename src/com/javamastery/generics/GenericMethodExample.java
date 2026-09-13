package com.javamastery.generics;

public class GenericMethodExample {
    static <T> T returnValue(T value){
        System.out.println(value);
        return value;
    }

    static <K,V> void printPair(K key , V value){
        System.out.println(key + " ==  " + value);
    }

    public static void main(String[] args) {
        returnValue(101);
        returnValue("Praveen");
        returnValue('C');
        returnValue(true);

        printPair("Product","Laptop");
        printPair(101,"Praveen");

    }
}
