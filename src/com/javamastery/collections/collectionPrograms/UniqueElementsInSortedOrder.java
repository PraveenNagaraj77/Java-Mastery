package com.javamastery.collections.collectionPrograms;

import java.util.Set;
import java.util.TreeSet;

public class UniqueElementsInSortedOrder {
    public static void main(String[] args) {
        int[] numbers = {5, 2, 8, 2, 1, 5, 9, 1};

        Set<Integer> numberSet = new TreeSet<>();
        for (int number : numbers){
            numberSet.add(number);
        }
        System.out.println(numberSet);
    }
}
