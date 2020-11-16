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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Base<?, ?> base = (Base<?, ?>) o;
        return Objects.equals(key, base.key) &&
                Objects.equals(value, base.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}