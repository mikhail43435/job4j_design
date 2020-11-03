package ru.job4j.design.list.arraylist;

import java.util.Arrays;
import java.util.Iterator;
import static java.util.Objects.checkIndex;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private T[] data;
    private int size;
    private int pointer;
    private int modCount;

    public SimpleArray() {
        data = (T[]) new Object[1];
    }

    protected int getSize() {
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
        if (size == 0) {
            data[0] = model;
            size++;
        } else {
            T[] tempArray;
            tempArray = data;
            data = (T[]) new Object[size++];
            System.arraycopy(tempArray, 0, data, 0, size - 1);
        }
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIt(this);
    }
}