package ru.job4j.design.map.HashMapArray;

import ru.job4j.design.map.HashMapArray.HashMapArrayIt;

import java.util.Arrays;
import java.util.Iterator;

import static java.util.Objects.checkIndex;

public class HashMapArray<K, V> {
    private Base[] data;
    private int index;
    private int size;
    private int modCount;

    public HashMapArray() {
        this.data =  new Base[10];
        this.size = 0;
    }

    public boolean insert(K key, V value) {
        Base newValue = new Base(key, value);
        for (int i = 0; i < size; i++) {
            if (data[i].equals(newValue)) {
                return false;
            }
        }
        if (size == data.length) {
            grow();
        }
        data[size++] = newValue;
        modCount++;
        return true;
    }

    public V get(K key) {
        for (int i = 0; i < size; i++) {
            if (data[i].getKey().equals(key)) {
                return (V) data[i].getValue();
            }
        }
        return null;
    }

    public boolean delete(K key) {
        for (int i = 0; i < size; i++) {
            if (data[i].getKey().equals(key)) {
                data[i] = data[size - 1];
                size--;
                return true;
            }
        }
        return false;
    }

    private void grow() {
        Base[] tempArray;
        tempArray = data;
        data = new Base[((size * 3) / 2) + 1];
        System.arraycopy(tempArray, 0, data, 0, size);
    }

    public int getSize() {
        return size;
    }

    public HashMapArrayIt iterator() {
        return new HashMapArrayIt(this);
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
