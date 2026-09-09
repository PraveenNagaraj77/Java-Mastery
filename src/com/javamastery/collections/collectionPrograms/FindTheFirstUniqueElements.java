package com.javamastery.collections.collectionPrograms;

import java.util.HashMap;
import java.util.Map;

public class FindTheFirstUniqueElements {
    public static void main(String[] args) {
        int[] numbers = {4, 5, 1, 2, 1, 4, 5};

        Map<Integer, Integer> frequency = new HashMap<>();

        for(int number : numbers){
            frequency.put(number,frequency.getOrDefault(number,0)+1);
        }

        for (int number : numbers){
            if(frequency.get(number)==1){
                System.out.println(number);
                break;
            }
        }

    }
}
