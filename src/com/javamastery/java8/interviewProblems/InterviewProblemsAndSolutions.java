package com.javamastery.java8.interviewProblems;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InterviewProblemsAndSolutions {
    public static void main(String[] args) {
        List<Integer> numbers =
                Arrays.asList(10, 25 ,10,7, 42, 18, 31, 50);

        Optional<Integer> maximum = numbers.stream()
                .filter(number -> number % 2 == 0)
                .max(Integer::compareTo);

        maximum.ifPresent(System.out::println);

        System.out.println("Find the second-highest number using Java 8 Streams.");

        Optional<Integer> result2 = numbers.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();
        result2.ifPresent(System.out::println);

        System.out.println("Count how many even numbers are present using Java 8 Streams");


        long result3 = numbers.stream()
                .filter(number->number%2==0)
                .count();
        System.out.println(result3);

        System.out.println("Sum of Numbers");

        Optional<Integer> result4 = numbers.stream()
                .reduce((a,b)->a+b);
        result4.ifPresent(System.out::println);


        System.out.println("Find Averagee");

        OptionalDouble result5 = numbers.stream()
                .mapToInt(Integer::intValue)
                .average();
        result5.ifPresent(System.out::println);

        System.out.println("Find Duplicate Elements");

        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();

        for (int num : numbers){
            if (seen.contains(num)){
               duplicates.add(num);
            }else{
                seen.add(num);
            }
        }

        System.out.println(duplicates);

        System.out.println("Find the First Non Repeated Element");

        Map<Integer,Long> frequency = numbers.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

       Optional<Integer> result = frequency.entrySet().stream()
               .filter(entry->entry.getValue()==1)
               .map(entry->entry.getKey())
               .findFirst();

       result.ifPresent(System.out::println);





    }
}
