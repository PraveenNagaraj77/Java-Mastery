package com.javamastery.collections.iterator;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListIteratorBasic {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();

        names.add("Java");
        names.add("Spring");
        names.add("React");
        names.add("Node");

        ListIterator<String> iterator = names.listIterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        while(iterator.hasPrevious()){
            System.out.println(iterator.previous());
        }

        while (iterator.hasNext()){
            String name = iterator.next();
            if(name.equals("React")){
                iterator.set("React.js");
            }
        }
        System.out.println(names);

    }
}
