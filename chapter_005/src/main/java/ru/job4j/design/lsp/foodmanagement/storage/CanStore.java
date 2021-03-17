package ru.job4j.design.lsp.foodmanagement.storage;

import ru.job4j.design.lsp.foodmanagement.food.Food;

public interface CanStore {

    boolean accept(Food f);

    void add(Food f);
}
