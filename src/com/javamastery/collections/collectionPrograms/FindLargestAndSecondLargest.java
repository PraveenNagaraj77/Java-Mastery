package com.javamastery.collections.collectionPrograms;

public class FindLargestAndSecondLargest {
    public static void main(String[] args) {
        int[] numbers = {10, 5, 20, 8, 20, 15};

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int number : numbers){
            if(number>largest){
                secondLargest=largest;
                largest = number;
            } else if (number>secondLargest && number!= largest) {
                secondLargest=number;
            }
        }

        System.out.println(largest);
        System.out.println(secondLargest);
    }
}
