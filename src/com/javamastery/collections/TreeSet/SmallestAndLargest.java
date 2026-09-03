package com.javamastery.collections.TreeSet;

import java.util.Set;
import java.util.TreeSet;

public class SmallestAndLargest {
    public static void main(String[] args) {
        int[] arr = {50, 10, 40, 20, 10, 30, 20};

        TreeSet<Integer> numbers = new TreeSet<>();
        for (int no : arr){
            numbers.add(no);
        }
        System.out.println(numbers);

        int smallest = numbers.first();
        int largest = numbers.last();


        System.out.println("Smallest: " + smallest);
        System.out.println("Largest: " + largest);


    }
}
