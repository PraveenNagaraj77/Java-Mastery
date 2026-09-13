package com.javamastery.generics;

public class GenericRepository<T> implements Repository<T> {

    private T value;

    @Override
    public void save(T value) {
        this.value = value;
    }

    @Override
    public T find() {
        return value;
    }
}