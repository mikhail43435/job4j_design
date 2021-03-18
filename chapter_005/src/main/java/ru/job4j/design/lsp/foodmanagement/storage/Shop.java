package ru.job4j.design.lsp.foodmanagement.storage;

import ru.job4j.design.lsp.foodmanagement.util.disc.ShopDiscounter;
import ru.job4j.design.lsp.foodmanagement.food.Food;

import static ru.job4j.design.lsp.foodmanagement.util.FoodSorter.getFoodCategory;

public class Shop extends Storage implements CanStore {

    public Shop() {
        super.name  = "Shop";
    }

    @Override
    public boolean accept(Food food) {
        int foodGroup = getFoodCategory(food);
        return foodGroup == 2 || foodGroup == 3;
    }

    @Override
    public void add(Food food) {
        if (getFoodCategory(food) == 3) {
            food.setDiscount(new ShopDiscounter().getDiscount(food));
        }
        super.add(food);
    }
}