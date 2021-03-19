package ru.job4j.design.lsp.foodmanagement.storage;

import ru.job4j.design.lsp.foodmanagement.util.ShelfLifeCalc;
import ru.job4j.design.lsp.foodmanagement.util.disc.ShopDiscounter;
import ru.job4j.design.lsp.foodmanagement.food.Food;

public class Shop extends Storage implements CanStore {

    public Shop() {
        super.name  = "Shop";
    }

    @Override
    public boolean accept(Food food) {
        int percent = ShelfLifeCalc.getPercentageOfShelfLifeLeft(food);
        return (percent >= 250 && percent <= 1000);
    }

    @Override
    public void add(Food food) {
        int percent = ShelfLifeCalc.getPercentageOfShelfLifeLeft(food);
        if (!(percent >= 250 && percent <= 1000)) return;
        if (percent > 750) {
            food.setDiscount(new ShopDiscounter());
        }
        super.add(food);
    }
}