package com.javamastery.collections.linkedHashSet;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class FirstLinkedHashSet {
    public static void main(String[] args) {
        Set<String> searchHistory = new LinkedHashSet<>();
        searchHistory.add("Java");
        searchHistory.add("React");
        searchHistory.add("Java");
        searchHistory.add("SQL");
        searchHistory.add("React");
        searchHistory.add("Node");
        searchHistory.add("Java");


        System.out.println(searchHistory);



    }


}
