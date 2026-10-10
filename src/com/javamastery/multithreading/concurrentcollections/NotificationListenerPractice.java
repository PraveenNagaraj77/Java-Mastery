package com.javamastery.multithreading.concurrentcollections;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NotificationListenerPractice {
    public static void main(String[] args) {
        List<String> listeners = new CopyOnWriteArrayList<>();

        listeners.add("Email Listener");
        listeners.add("SMS Listener");

        Iterator<String> iterator = listeners.iterator();

        listeners.add("Push Listener");

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println("Current List : " + listeners);
    }
}
