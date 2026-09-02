package com.javamastery.collections.arrayList;

import java.util.ArrayList;
import java.util.List;

public class SecondLargestDistinctNumber {

    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<>();

        numbers.add(45);
        numbers.add(12);
        numbers.add(89);
        numbers.add(34);
        numbers.add(67);
        numbers.add(89);

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int number : numbers) {

            if (number > largest) {
                secondLargest = largest;
                largest = number;

            } else if (number > secondLargest && number != largest) {
                secondLargest = number;
            }
        }

        System.out.println("Largest: " + largest);
        System.out.println("Second Largest: " + secondLargest);
    }
}