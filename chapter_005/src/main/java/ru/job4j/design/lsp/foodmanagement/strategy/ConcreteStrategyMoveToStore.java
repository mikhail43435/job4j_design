package ru.job4j.design.lsp.foodmanagement.strategy;

import ru.job4j.design.lsp.foodmanagement.food.Food;
import ru.job4j.design.lsp.foodmanagement.storage.Storage;

public class ConcreteStrategyMoveToStore implements Strategy {

    @Override
    public void sort(Storage storage, Food food) {
        storage.addItem(food);
    }
}
