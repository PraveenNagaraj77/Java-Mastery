package com.javamastery.collections.collectionPrograms;

import java.util.HashSet;
import java.util.Set;

public class FindCommonElements {
    public static void main(String[] args) {
        int[] numbers1 = {10, 20, 30, 40, 50};
        int[] numbers2 = {30, 40, 60, 70};

        Set<Integer> numbersSet = new HashSet<>();
        for (int num : numbers1){
            numbersSet.add(num);
        }

        for(int number : numbers2){
            if(numbersSet.contains(number)){
                System.out.println(number);
            }
        }

    }
}
