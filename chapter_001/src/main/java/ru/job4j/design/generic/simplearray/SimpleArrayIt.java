package ru.job4j.design.generic.simplearray;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayIt<T> implements Iterator<T> {
    private final SimpleArray<T> data;
    private int pointer = 0;

    public SimpleArrayIt(SimpleArray<T> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
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
