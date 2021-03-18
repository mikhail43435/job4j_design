package ru.job4j.design.lsp.foodmanagement.util.disc;

import ru.job4j.design.lsp.foodmanagement.food.Food;

public class ShopDiscounter implements Discounter {

    public int getDiscount(Food food) {
        return 50;
    }

}