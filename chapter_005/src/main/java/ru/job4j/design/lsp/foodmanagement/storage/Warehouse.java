package ru.job4j.design.lsp.foodmanagement.storage;

import ru.job4j.design.lsp.foodmanagement.food.Food;

import static ru.job4j.design.lsp.foodmanagement.util.FoodSorter.getFoodCategory;

public class Warehouse extends Storage implements CanStore {

    public Warehouse() {
        super.name  = "Warehouse";
    }

    @Override
    public boolean accept(Food food) {
        return getFoodCategory(food) == 1;
    }
}