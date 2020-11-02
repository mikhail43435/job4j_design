package ru.job4j.design.iterator;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class FlatMap<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor;

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;

        //if (data == Collections.emptyIterator())

        if (data != null) {
            cursor = Collections.emptyIterator();//data.next();
        }
    }

    @Override
    public boolean hasNext() {
        if (cursor.hasNext()) {
            return true;
        } else return data.hasNext() && data.next().hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (!cursor.hasNext()) {
            System.out.println(data.getClass());
            System.out.println(data.hasNext());
            cursor = data.next();
        }
        return cursor.next();
    }

    public static void main(String[] args) {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        while (flat.hasNext()) {
            System.out.println(flat.next());
        }
    }
}