package ru.job4j.design.lsp.foodmanagement.storage;

import ru.job4j.design.lsp.foodmanagement.food.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class Storage {

    List<Food> items = new ArrayList<>();

    public void addItem(Food food) {
        items.add(food);
    }

    public void addItemWithDiscount(Food food, int discount) {
        food.setDiscount(discount);
        items.add(food);
    }

    public void display() {
        System.out.println(items);
    }

    public List<Food> getItemsList() {
        return items;
    }
}
