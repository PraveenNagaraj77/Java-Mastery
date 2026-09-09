package com.javamastery.collections.collectionPrograms;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class MixedCollectionProblem {
    public static void main(String[] args) {
        int[] numbers = {10, 20, 10, 30, 20, 40, 30, 50};

        Set<Integer> numberSet = new HashSet<>();
        Set<Integer> resultSet = new LinkedHashSet<>();

        for (int number : numbers){
            if(!numberSet.contains(number)){
                numberSet.add(number);
            }else{
                resultSet.add(number);
            }
        }

        System.out.println(resultSet);
    }
}
