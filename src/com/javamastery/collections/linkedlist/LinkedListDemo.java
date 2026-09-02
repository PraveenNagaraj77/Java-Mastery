package com.javamastery.collections.linkedlist;

import java.util.LinkedList;
import java.util.List;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<String> technologies = new LinkedList<>();
        technologies.add("Java");
        technologies.add("Spring Boot");
        technologies.add("React");
        technologies.add("SQL");

        System.out.println(technologies);

        technologies.addFirst("Docker");
        technologies.addLast("AWS");

        System.out.println(technologies);

        System.out.println("First :" + technologies.getFirst());
        System.out.println("Last :" + technologies.getLast());

        technologies.removeFirst();
        technologies.removeLast();
        System.out.println(technologies);

    }
}
