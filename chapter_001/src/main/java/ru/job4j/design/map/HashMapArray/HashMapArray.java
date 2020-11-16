package ru.job4j.design.map.HashMapArray;

import ru.job4j.design.map.HashMapArray.HashMapArrayIt;

import java.util.Arrays;
import java.util.Iterator;
import static java.util.Objects.checkIndex;

public class HashMapArray<K, V> {
    private Base[] data;
    //private int index;
    private int size;
    //private int modCount;

    public HashMapArray() {
        this.data =  new Base[10];
        this.size = 0;
    }

    public boolean insert(K key, V value) {
        boolean rsl = true;
        Base newValue = new Base(key, value);
            if (Arrays.asList(data).contains(newValue)) {
                rsl = false;
            }
        if (size / data.length >= 0.75) {
            grow();
        }
        data[size++] = newValue;
        return rsl;
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
        data = new Base[size * 2];
        for (int index = 0; index < size; index++) {
            data[index] = tempArray[index];
        }
        //System.out.println("grow to: " + data.length);
    }

    public int getSize() {
        return size;
    }

    public HashMapArrayIt iterator() {
        return new HashMapArrayIt(this);
    }

    @Override
    public String toString() {
        return "SimpleArray{"
                +  "data=" + Arrays.toString(data)
                //+  ", index=" + index
                +  ", size=" + size
                +  '}';
    }
}
