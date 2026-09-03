package com.javamastery.collections.hashset;

import java.util.HashSet;
import java.util.Set;

public class DetectDuplicates {
    public static void main(String[] args) {
        int[] numbers = {10,20,30,40,20,50};

        Set<Integer> duplicateNumbers = new HashSet<>();
        int count = 0;
        for (int number : numbers){
            if(!duplicateNumbers.add(number)){
                System.out.println("Duplicate Found " +number);
            }
        }
    }
}
