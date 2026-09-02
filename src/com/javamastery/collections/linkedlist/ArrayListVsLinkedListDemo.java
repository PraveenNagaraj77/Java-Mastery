package com.javamastery.collections.linkedlist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayListVsLinkedListDemo {
    public static void main(String[] args) {
        List<String> technologies = new ArrayList<>();
        technologies.add("Java");
        technologies.add("Spring Boot");
        technologies.add("React");
        technologies.add("Node.js");
        technologies.add("SQL");

        LinkedList<String> tech = new LinkedList<>();
        tech.add("Java");
        tech.add("Spring Boot");
        tech.add("React");
        tech.add("Node.js");
        tech.add("SQL");


        System.out.println("ArrayList : "+technologies);
        System.out.println("LinkedList :"+tech);


        technologies.get(2);
        tech.get(2);

        technologies.add(0,"Docker");
        tech.addFirst("Docker");

        technologies.add("AWS");
        tech.addLast("AWS");


        technologies.remove(0);
        tech.removeFirst();

        technologies.remove(technologies.size() -1 );

        tech.removeLast();


        System.out.println("ArrayList : "+technologies);
        System.out.println("LinkedList :"+tech);




    }
}
