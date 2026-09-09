package com.javamastery.collections.collectionPrograms;

import java.util.HashSet;
import java.util.Set;

public class FindCommonElementsWithNoDuplicates {
    public static void main(String[] args) {
        int[] numbers1 = {1, 2, 2, 3, 4, 5};
        int[] numbers2 = {2, 2, 3, 6, 7};

        Set<Integer> numbersSet = new HashSet<>();
        Set<Integer> resultSet = new HashSet<>();
        for (int number : numbers1){
            numbersSet.add(number);
        }

        for (int number : numbers2){
            if(numbersSet.contains(number)){
                resultSet.add(number);
            }
        }
        System.out.println(resultSet);






    }
}
