package ru.job4j.design.generic.store;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {

        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                mem.set(i, model);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        T value = findById(id);
        if (value != null) {
            mem.remove((value));
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        for (T value : mem) {
            if (value.getId().equals(id))
                return value;
        }
        return null;
     }
}