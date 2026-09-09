package com.javamastery.collections.collectionPrograms;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class RemoveDuplicates {
    public static void main(String[] args) {
        int[] numbers = {10, 20, 10, 30, 20, 40};
        Set<Integer> numberSet = new LinkedHashSet<>();

        for (int no : numbers){
            numberSet.add(no);
        }
        System.out.println(numberSet);
    }
}
