package com.javamastery.dsa.hashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoArrays {
    public static Set<Integer> findIntersection(int[] arr1, int[] arr2){
        Set<Integer> numberSet = new HashSet<>();
        for (int i = 0; i < arr1.length; i++) {
            numberSet.add(arr1[i]);
        }

        Set<Integer> resultSet = new HashSet<>();

        for (int i = 0; i < arr2.length; i++) {
            if(numberSet.contains(arr2[i])){
                resultSet.add(arr2[i]);
            }
        }
        return resultSet;

    }
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 2, 3, 4};
        int[] arr2 = {2, 3, 3, 5};
        System.out.println(findIntersection(arr1,arr2));
    }
}
