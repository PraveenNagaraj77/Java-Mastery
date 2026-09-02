package com.javamastery.collections.arrayList;

import java.util.ArrayList;
import java.util.List;

public class FindMaximum {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(45);
        numbers.add(12);
        numbers.add(89);
        numbers.add(34);
        numbers.add(67);

        System.out.println(numbers);


        int maximum = numbers.get(0);

        for (int i=0;i<numbers.size();i++){
            if(numbers.get(i)>maximum){
                maximum = numbers.get(i);
            }
            }
        System.out.println(maximum);


    }
}
