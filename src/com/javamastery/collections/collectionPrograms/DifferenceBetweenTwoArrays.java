package com.javamastery.collections.collectionPrograms;

import java.util.HashSet;
import java.util.Set;

public class DifferenceBetweenTwoArrays {
    public static void main(String[] args) {
        int[] numbers1 = {10, 20, 30, 40, 50};
        int[] numbers2 = {30, 40, 60};

        Set<Integer> numbersSet = new HashSet<>();

        for (int num : numbers2){
            numbersSet.add(num);
        }

        for (int num : numbers1){
            if(!numbersSet.contains(num)){
                System.out.println(num);
            }
        }

    }
}
