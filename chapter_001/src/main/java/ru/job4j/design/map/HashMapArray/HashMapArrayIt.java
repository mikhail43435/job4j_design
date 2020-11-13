package ru.job4j.design.map.HashMapArray;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashMapArrayIt<K, T> implements Iterator {

    private final HashMapArray<K, T> data;
    private int pointer;
    //private final int expectedModCount;

    public HashMapArrayIt(HashMapArray<K, T> data) {
        //this.expectedModCount = data.getModCount();
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        /*if (!(expectedModCount == data.getModCount())) {
            throw new ConcurrentModificationException();
        }*/
        return pointer < data.getSize();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return null;//data[pointer++];
    }
}
