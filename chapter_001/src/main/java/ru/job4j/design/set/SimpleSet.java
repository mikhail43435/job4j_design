package ru.job4j.design.set;

import ru.job4j.design.list.arraylist.SimpleArray;

import java.util.Iterator;

public class SimpleSet<T> {
    private final SimpleArray<T> data;

    public SimpleSet() {
        this.data = new SimpleArray<>(10);
    }

    public void add(T model) {
        Iterator<T> it = data.iterator();
        while (it.hasNext()) {
            if (it.next().equals(model)) return;
        }
        data.add(model);
    }

    public T get(int index) {
        return data.get(index);
    }

    public int getSize() {
        return data.getSize();
    }

    public Iterator<T> iterator() {
        return data.iterator();
    }

}
