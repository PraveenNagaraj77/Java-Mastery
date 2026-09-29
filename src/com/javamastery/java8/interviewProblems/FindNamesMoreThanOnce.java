package com.javamastery.java8.interviewProblems;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class FindNamesMoreThanOnce {
    public static void main(String[] args) {
        List<String> names = Arrays.asList(
                "Java", "Spring", "Java", "React",
                "Spring", "Java", "Node", "React"
        );

        Map<String,Long> result = names.stream()
                .collect(Collectors.groupingBy(
                        name->name,
                        Collectors.counting()
                )).entrySet().stream().filter(entry->entry.getValue()>1).collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));

        result.forEach((name,count)-> System.out.println(name+ " : " + count));


    }

}
