package com.javamastery.collections.TreeSet;

import java.util.TreeSet;

public class TailSetProblem {
    public static void main(String[] args) {
        int[] arr = {10,25,5,40,15,30,20,35};

        TreeSet<Integer> numbers = new TreeSet<>();

        for(int no :arr){
           numbers.add(no);
        }
        System.out.println(numbers);

        System.out.println(numbers.tailSet(20));

        System.out.println(numbers.descendingSet());

    }
}
