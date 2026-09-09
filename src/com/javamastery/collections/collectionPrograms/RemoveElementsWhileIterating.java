package com.javamastery.collections.collectionPrograms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class RemoveElementsWhileIterating {
    public static void main(String[] args) {
        List<String> technologies = new ArrayList<>(
                Arrays.asList("Java", "Python", "Java", "React", "Java", "Node")
        );

        Iterator<String> iterator = technologies.iterator();

        while (iterator.hasNext()){
            String name = iterator.next();
            if(name.equals("Java")){
                iterator.remove();
            }
        }

        System.out.println(technologies);
    }
}
