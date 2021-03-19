package ru.job4j.design.lsp.foodmanagement.storage;

import ru.job4j.design.lsp.foodmanagement.food.Food;
import ru.job4j.design.lsp.foodmanagement.util.ShelfLifeCalc;

public class Trash extends Storage implements CanStore {

    public Trash() {
        super.name  = "Trash";
    }

    @Override
    public boolean accept(Food food) {
        return ShelfLifeCalc.getPercentageOfShelfLifeLeft(food) > 1000;
    }
}
