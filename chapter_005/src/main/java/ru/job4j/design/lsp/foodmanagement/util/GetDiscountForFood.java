package ru.job4j.design.lsp.foodmanagement.util;

import ru.job4j.design.lsp.foodmanagement.food.Food;

public final class GetDiscountForFood {

    private GetDiscountForFood() {
        throw new IllegalStateException("Utility class");
    }

    public static int getDiscount(Food food) {
        return 50;
    }
}
