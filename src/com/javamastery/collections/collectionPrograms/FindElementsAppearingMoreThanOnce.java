package com.javamastery.collections.collectionPrograms;

import java.util.*;

public class FindElementsAppearingMoreThanOnce {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 2, 4, 1, 5, 2};

        Map<Integer,Integer> frequency = new HashMap<>();
        Set<Integer> Duplicate = new HashSet<>();
        for (int number : numbers){
            frequency.put(number,frequency.getOrDefault(number,0)+1);
            if (frequency.get(number)>1){
                Duplicate.add(number);
            }
        }
        System.out.println(Duplicate);

    }
}
