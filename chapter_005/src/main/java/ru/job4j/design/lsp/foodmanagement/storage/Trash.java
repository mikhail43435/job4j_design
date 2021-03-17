package ru.job4j.design.lsp.foodmanagement.storage;

import ru.job4j.design.lsp.foodmanagement.food.Food;

import static ru.job4j.design.lsp.foodmanagement.util.SortFood.getFoodGroupByDate;

public class Trash extends Storage implements CanStore {

    public Trash() {
        super.name  = "Trash";
    }

    @Override
    public boolean accept(Food food) {
        return getFoodGroupByDate(food) == 4;
    }
}
