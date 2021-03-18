package ru.job4j.design.lsp.foodmanagement.util.disc;

import ru.job4j.design.lsp.foodmanagement.food.Food;

public interface Discounter {

    int getDiscount(Food food);

}