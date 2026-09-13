package com.javamastery.generics;

public class GenericInterfaceExample {
    public static void main(String[] args) {
        GenericRepository<String> stringGenericRepository = new GenericRepository<>();
        stringGenericRepository.save("Laptop");
        System.out.println(stringGenericRepository.find());

        GenericRepository<Integer> integerGenericRepository = new GenericRepository<>();
        integerGenericRepository.save(100);
        System.out.println(integerGenericRepository.find());
    }
}
