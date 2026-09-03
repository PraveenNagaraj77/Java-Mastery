package com.javamastery.collections.hashset;

import java.util.HashSet;
import java.util.Set;

public class FirstRepeatedElement {
    public static void main(String[] args) {
        int[] numbers = {10, 5, 3, 4, 3, 5, 6};
        int repeatedNumber = -1;
        Set<Integer> num = new HashSet<>();
        for (int no : numbers){
            if (!num.add(no)) {
                repeatedNumber = no;
                break;
            }
        }
        System.out.println(num);
        System.out.println("Repeated Numbers : " +repeatedNumber);


    }
}
