package com.javamastery.java8.interviewProblems;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class FindAllNameWithFourChar {
    public static void main(String[] args) {
        List<String> names = Arrays.asList(
                "Praveen", "Arun", "Priya", "Rahul", "Karthik", "Anu"
        );

       List<String> result =  names.stream().filter(name->name.length()>4)
                .map(name->name.toUpperCase())
               .collect(Collectors.toList());
        System.out.println(result);
    }
}
