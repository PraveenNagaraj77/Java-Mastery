package com.javamastery.collections.collectionPrograms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindDuplicates {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 2, 4, 5, 3, 6};

        Set<Integer> numberSet = new HashSet<>();
        List<Integer> duplicates = new ArrayList<>();
        for (int number : numbers){
            if(numberSet.contains(number)){
                duplicates.add(number);
            }else{
                numberSet.add(number);
            }
        }
        System.out.println(duplicates);
    }
}
