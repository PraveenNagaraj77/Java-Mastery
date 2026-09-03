package com.javamastery.collections.TreeSet;

import java.util.TreeSet;

public class FindNumbersWithRange {
    public static void main(String[] args) {
        int[] arr = {10, 25, 5, 40, 15, 30, 20, 35};

        TreeSet<Integer> numberSet = new TreeSet<>();

        for (int no : arr){
            numberSet.add(no);
        }
        System.out.println(numberSet);

        System.out.println(numberSet.subSet(15, 31));

    }
}
