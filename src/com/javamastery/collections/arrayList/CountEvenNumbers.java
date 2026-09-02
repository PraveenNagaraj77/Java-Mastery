package com.javamastery.collections.arrayList;

import java.util.ArrayList;
import java.util.List;

public class CountEvenNumbers {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();

        numbers.add(10);
        numbers.add(15);
        numbers.add(22);
        numbers.add(33);
        numbers.add(40);
        numbers.add(51);
        numbers.add(64);

        System.out.println(numbers);

        int count =0;
        for (int i = 0; i < numbers.size(); i++) {
            if(numbers.get(i)%2==0){
                count++;
            }
        }
        System.out.println(count);
    }

}
