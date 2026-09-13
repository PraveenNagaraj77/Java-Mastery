package com.javamastery.generics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericBasicExample {
    public static void main(String[] args) {
        Map<String, Integer> productStock = new HashMap<>();
        productStock.put("Laptop", 20);
        productStock.put("Keyboard", 50);
        productStock.put("Mouse", 100);

        for (Map.Entry<String,Integer> product : productStock.entrySet()){
            System.out.println(product.getKey() + " " + product.getValue());
        }
    }
}
