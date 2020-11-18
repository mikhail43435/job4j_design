package ru.job4j.design.map.HashMapArray;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashMapArrayIt<K, V> implements Iterator<V> {
    private final HashMapArray.Node[] array;
    private final int expectedModCount;
    private int pointer;
    private final HashMapArray<K, V> hashMapArray;

    public HashMapArrayIt(HashMapArray.Node[] array, int modCount, HashMapArray<K, V> hashMapArray) {
        this.expectedModCount = modCount;
        this.array = array;
        this.hashMapArray = hashMapArray;
    }

    @Override
    public boolean hasNext() {
        if (!(expectedModCount == hashMapArray.getModCount())) {
            throw new ConcurrentModificationException();
        }
        while (pointer < array.length && array[pointer] == null) {
            pointer++;
        }
        return pointer < array.length;
    }

    @Override
    public V next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return (V) array[pointer++].getValue();
    }

}