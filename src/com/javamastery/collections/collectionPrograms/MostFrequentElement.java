package com.javamastery.collections.collectionPrograms;

import java.util.LinkedHashMap;
import java.util.Map;

public class MostFrequentElement {
    public static void main(String[] args) {
        int[] numbers = {10, 20, 10, 30, 20, 10, 20};

        Map<Integer,Integer> frequency = new LinkedHashMap<>();

        for(int number : numbers){
            frequency.put(number,frequency.getOrDefault(number,0)+1);
        }

        int maxFrequency =0;
        int mostFrequent = 0;

        for (int number  : numbers){
            int count = frequency.get(number);
            if(count>maxFrequency){
                maxFrequency = count;
                mostFrequent = number;
            }

        }
        System.out.println(mostFrequent);



    }

}
