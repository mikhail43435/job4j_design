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
        for (T value : mem) {
            if (checkId(value, id)) {
                mem.remove(value);
                return true;
            }
        }
        return false;
    }

    @Override
    public T findById(String id) {
        for (T value : mem) {
            if (checkId(value, id))
                return value;
        }
        return null;
     }

     private boolean checkId(T value, String id) {
        return value.getId().equals(id);
     }

}