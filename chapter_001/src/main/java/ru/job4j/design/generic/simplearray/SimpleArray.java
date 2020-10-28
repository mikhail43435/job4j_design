package ru.job4j.design.generic.simplearray;

import java.util.Arrays;
import static java.util.Objects.checkIndex;

public class SimpleArray<T> {
    private final T[] data;
    private int index = -1;
    private final int size;

    public SimpleArray(int size) {
        this.data = (T[]) new Object[size];
        this.size = size;
    }

    public void add (T value) {
        if (checkIndex((index + 1 ), size) < size)
            data[++index] = value;
    }

    public T get(int indexGet) {
        //if (checkIndex(index, size) < size) {
        checkIndex(indexGet, index + 1);
        return data[indexGet];
        /*} else {
            throw new IndexOutOfBoundsException();
        }*/
    }

    public void set(int indexSet, T model)  {
        checkIndex(indexSet, index +1 );
        data[indexSet] = model;
    }

    public void remove(int indexRemove) {
        checkIndex(indexRemove, index + 1);
        System.arraycopy(data, indexRemove + 1, data, indexRemove , index - indexRemove );
        index--;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "SimpleArray{" +
                "data=" + Arrays.toString(data) +
                ", index=" + index +
                ", size=" + size +
                '}';
    }
}
