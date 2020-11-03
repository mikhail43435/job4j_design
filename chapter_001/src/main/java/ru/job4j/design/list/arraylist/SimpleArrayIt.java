package ru.job4j.design.list.arraylist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayIt<T> implements Iterator<T> {

    private final SimpleArray<T> data;
    private int pointer;
    private final int expectedModCount;

    public SimpleArrayIt(SimpleArray<T> data) {
        this.expectedModCount = data.getModCount();
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (!(expectedModCount == data.getModCount())) {
            throw new ConcurrentModificationException();
        }

        return pointer < data.getSize();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data.get(pointer++);
    }
}
