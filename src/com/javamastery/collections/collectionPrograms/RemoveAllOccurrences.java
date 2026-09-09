package com.javamastery.collections.collectionPrograms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RemoveAllOccurrences {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 2, 4, 2, 5};
        int target = 2;

        List<Integer> result = new ArrayList<>();


        for (int no : numbers){
           result.add(no);
        }
        Iterator<Integer> iterator = result.iterator();
        while (iterator.hasNext()) {
            int no = iterator.next();

            if (no == target) {
                iterator.remove();
            }
        }

        System.out.println(result);

    }
}
