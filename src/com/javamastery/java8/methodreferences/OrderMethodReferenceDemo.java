package com.javamastery.java8.methodreferences;

import java.util.function.Consumer;
import java.util.function.Function;

public class OrderMethodReferenceDemo {
    public static void main(String[] args) {
        //Static Method reference
        //classname :: staticmethod

        Function<String,Integer> orderIdParser = Integer::parseInt;
        Integer orderId = orderIdParser.apply("1001");
        System.out.println("Order Id : " +orderId);

//        Instance Method Reference

        OrderService orderService = new OrderService();

        Consumer<Order> orderPrinter = orderService::printOrder;

        Order order = new Order(
                1001,"Praveen",2499.00
        );

        orderPrinter.accept(order);

        //3.Instance Method Reference Arbitary Object

        Function<Order,String> customerNameExtractor = Order::getCustomerName;

        String customerName = customerNameExtractor.apply(order);

        System.out.println("Customer : "+ customerName);


        //Constructor Reference -- Classname :: new

        Function<String,Order> orderCreator = Order::new;
        Order newOrder = orderCreator.apply("Kowsalya");
        System.out.println("New Order Customer : " + newOrder.getCustomerName());

    }
}
