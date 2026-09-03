package com.javamastery.collections.linkedHashSet;

import java.util.LinkedHashSet;
import java.util.Set;

public class PreserveFirstOccurrence {
    public static void main(String[] args) {
        String[] users = {
                "Praveen",
                "Rahul",
                "Praveen",
                "Anjali",
                "Rahul",
                "Vijay"
        };

        Set<String> usersSet = new LinkedHashSet<>();
        for (String usernames : users){
            usersSet.add(usernames);
        }
        System.out.println(usersSet);

    }
}
