package ru.job4j.design.map.HashMapArray;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class HashMapArray<K, V> implements Iterator {
    private Node[] data;
    private int size;
    private final double loadFactor = 0.75;
    private int threshold;
    private int pointer;

    public HashMapArray() {
        this.data =  new Node[16];
    }

    public boolean insert(K key, V value) {
        boolean rsl = true;
        Node newValue = new Node(key, value);
        int hashNewValue = newValue.hashCode() % data.length;
        while (data[hashNewValue] != null) {
            return false;
        }
        data[hashNewValue] = newValue;
        size++;
        if ((double) size / (double) data.length >= loadFactor) {
            grow();
        }
        return rsl;
    }

    public V get(K key) {
        int hash = new Node(key).hashCode() % data.length;
        if (data[hash] != null) {
            return (V) data[hash].getValue();
        }
        return null;
    }

    public boolean delete(K key) {
        int hash = new Node(key).hashCode() % data.length;
        if (data[hash] != null) {
            data[hash] = null;
            return true;
        }
        return false;
    }

    private void grow() {
        Node[] oldArray = data;
        int newLenght = data.length * 2;
        data = new Node[newLenght];
        for (int index = 0; index < oldArray.length; index++) {
            //System.out.println(tempArray[index].hashCode() % newLenght);
            if (oldArray[index] != null) {
                data[oldArray[index].hashCode() % newLenght] = oldArray[index];
            }
        }
    }

/*    public int getSize() {
        return size;
    }

    public int getLength() {
        return data.length;
    }*/

    @Override
    public String toString() {
        return "SimpleArray{"
                +  "data=" + Arrays.toString(data)
                //+  ", index=" + index
                //+  ", size=" + size
                +  '}';
    }

    @Override
    public boolean hasNext() {
        while (pointer < data.length && data[pointer] == null) {
            pointer++;
        }
        return pointer < data.length;
    }

    @Override
    public V next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return (V) data[pointer++].getValue();
    }

    private class Node<K, V> {
        private final K key;
        private V value;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private Node(K key) {
            this.key = key;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<K, V>) o;
            return Objects.equals(key, node.key)
                    && Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

        private V getValue() {
            return this.value;
        }

        private K getKey() {
            return this.key;
        }

    }
}
