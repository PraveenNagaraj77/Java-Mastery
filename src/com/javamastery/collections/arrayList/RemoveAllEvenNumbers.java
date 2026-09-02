package com.javamastery.collections.arrayList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RemoveAllEvenNumbers {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(15);
        numbers.add(22);
        numbers.add(33);
        numbers.add(40);
        numbers.add(51);


        System.out.println(numbers);

        Iterator<Integer> iterator = numbers.iterator();

        while (iterator.hasNext()){
            Integer number = iterator.next();
            if(number % 2 == 0){
                iterator.remove();
            }
        }

        System.out.println(numbers);

    }
}
