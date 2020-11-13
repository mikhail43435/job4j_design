package ru.job4j.design.map.HashMapArray;

import java.util.Objects;

public class Base<K, V> {
    private final K key;
    private final V value;

    public Base(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public V getValue() {
        return this.value;
    }

    public K getKey() {
        return this.key;
    }

    @Override
    public boolean equals(Object o) {
        return this.hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}