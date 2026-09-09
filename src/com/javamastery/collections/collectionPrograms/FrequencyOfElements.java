package com.javamastery.collections.collectionPrograms;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FrequencyOfElements {
    public static void main(String[] args) {
        int[] numbers = {10, 20, 10, 30, 20, 10};
        Map<Integer,Integer> frequency = new LinkedHashMap<>();

       for (int number : numbers){
           frequency.put(number,frequency.getOrDefault(number,0)+1);
       }

        System.out.println(frequency);

    }
}
