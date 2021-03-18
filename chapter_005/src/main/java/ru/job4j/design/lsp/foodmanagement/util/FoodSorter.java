package ru.job4j.design.lsp.foodmanagement.util;

import ru.job4j.design.lsp.foodmanagement.food.Food;

public final class FoodSorter {

    private FoodSorter() {
        throw new IllegalStateException("Utility class");
    }

    public static int getFoodCategory(Food food) {
        int ratio = ShelfLifeCalc.getPercentageOfShelfLifeLeft(food);
        if (ratio <= 0) {
            return 0;
        } else if (ratio < 250) {
            return 1;
        } else if (ratio <= 750) {
            return 2;
        } else if (ratio <= 1000) {
            return 3;
        } else {
            return 4;
        }
    }
}