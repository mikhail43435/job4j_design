package ru.job4j.design.lsp.foodmanagement.storage;

import ru.job4j.design.lsp.foodmanagement.util.GetDiscountForFood;
import ru.job4j.design.lsp.foodmanagement.food.Food;

import static ru.job4j.design.lsp.foodmanagement.util.SortFood.getFoodGroupByDate;

public class Shop extends Storage implements CanStore {

    public Shop() {
        super.name  = "Shop";
    }

    @Override
    public boolean accept(Food food) {
        int foodGroup = getFoodGroupByDate(food);
        return foodGroup == 2 || foodGroup == 3;
    }

    @Override
    public void add(Food food) {
        if (getFoodGroupByDate(food) == 3) {
            food.setDiscount(GetDiscountForFood.getDiscount(food));
        }
        super.add(food);
    }
}
