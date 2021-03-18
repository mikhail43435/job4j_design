package ru.job4j.design.lsp.foodmanagement.storage;

import ru.job4j.design.lsp.foodmanagement.food.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class Storage {

    protected String name;

    private final List<Food> items = new ArrayList<>();

    public abstract boolean accept(Food food);

    public void add(Food food) {
        items.add(food);
    }

    public List<Food> getItemsList() {
        return items;
    }

    public String getName() {
        return name;
    }

    public void display() {
        System.out.println("======================");
        System.out.println(this.getName());
        items.forEach(System.out::println);
    }
}