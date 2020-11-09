package ru.job4j.design.set;

import ru.job4j.design.list.arraylist.SimpleArray;

public class SimpleSet<T> extends SimpleArray<T> {

    @Override
    public void add(T model) {
        for (T value : data) {
            if (value == model) return;
        }
        if (size == data.length) grow();
        data[size++] = model;
        modCount++;
    }
}
