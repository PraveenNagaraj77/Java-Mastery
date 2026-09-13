package com.javamastery.generics;

public interface Repository<T> {
    void save(T value);
    T find();
}
