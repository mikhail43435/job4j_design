package ru.job4j.design.lsp.foodmanagement.storage;

import ru.job4j.design.lsp.foodmanagement.food.Food;
import ru.job4j.design.lsp.foodmanagement.util.ShelfLifeCalc;

public class Warehouse extends Storage implements CanStore {

    public Warehouse() {
        super.name  = "Warehouse";
    }

    @Override
    public boolean accept(Food food) {
        int percent = ShelfLifeCalc.getPercentageOfShelfLifeLeft(food);
        return (percent >= 0 && percent < 250);
    }
}