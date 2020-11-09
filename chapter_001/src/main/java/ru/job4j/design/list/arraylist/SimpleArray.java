package ru.job4j.design.list.arraylist;

import java.util.Arrays;
import java.util.Iterator;
import static java.util.Objects.checkIndex;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    protected T[] data;
    protected int size;
    protected int pointer;
    protected int modCount;

    public SimpleArray() {
        data = (T[]) new Object[10];
    }

    public SimpleArray(int size) {
        data = (T[]) new Object[size];
    }

    public int getSize() {
        return size;
    }

    protected int getModCount() {
        return modCount;
    }

    public T get(int index) {
        checkIndex(index, size);
        return data[index];
    }

    public void add(T model) {
        if (size == data.length) grow();
        data[size++] = model;
        modCount++;
    }

    protected void grow() {
        T[] tempArray;
        tempArray = data;
        data = (T[]) new Object[size * 2];
        System.arraycopy(tempArray, 0, data, 0, size);
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIt(this);
    }
}