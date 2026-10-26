package com.javamastery.dsa.hashing;

import java.util.HashSet;
import java.util.Set;

public class FindTheDuplicateNumber {
    public static int findDuplicate(int[] arr){
        Set<Integer> numberSet = new HashSet<>();
        for (int num : arr){
            if(!numberSet.add(num)) {
                return num;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 2};
        System.out.println(findDuplicate(arr));
    }
}
