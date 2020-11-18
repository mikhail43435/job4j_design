package ru.job4j.design.map.HashMapArray;

import java.util.*;

public class HashMapArray<K, V> implements Iterable<V> {
    private Node[] data;
    private int size;
    private final double loadFactor = 0.75;
    private int modCount;

    public HashMapArray() {
        this.data =  new Node[16];
    }

    public boolean insert(K key, V value) {
        boolean rsl = true;
        Node newValue = new Node(key, value);
        int hashNewValue = newValue.hashCode() & 0x7fffffff % data.length;
        if (data[hashNewValue] != null) {
            return false;
        }
        data[hashNewValue] = newValue;
        size++;
        if ((double) size / (double) data.length >= loadFactor) {
            grow();
        }
        modCount++;
        return rsl;
    }

    public V get(K key) {
        Node<K, V> newNode = new Node(key);
        int hash = newNode.hashCode() & 0x7fffffff % data.length;
        if (data[hash] != null && data[hash].equals(newNode)) {
            return (V) data[hash].getValue();
        }
        return null;
    }

    public boolean delete(K key) {
        Node<K, V> newNode = new Node(key);
        int hash = newNode.hashCode() & 0x7fffffff % data.length;
        if (data[hash] != null && data[hash].equals(newNode)) {
            data[hash] = null;
            modCount++;
            return true;
        }
        return false;
    }

    private void grow() {
        Node[] oldArray = data;
        int newLenght = data.length * 2;
        data = new Node[newLenght];
        for (int index = 0; index < oldArray.length; index++) {
            if (oldArray[index] != null) {
                data[oldArray[index].hashCode() % newLenght] = oldArray[index];
            }
        }
    }

    protected int getModCount() {
        return modCount;
    }

    @Override
    public Iterator<V> iterator() {
        return new HashMapArrayIt(this.data, modCount, this);
    }

    protected class Node<K, V> {
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
            return Objects.equals(key, node.key);
                    //&& Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

        V getValue() {
            return this.value;
        }

        private K getKey() {
            return this.key;
        }

    }
}
