package ru.job4j.design.lsp.foodmanagement;

import ru.job4j.design.lsp.foodmanagement.food.Food;
import ru.job4j.design.lsp.foodmanagement.storage.Storage;
import ru.job4j.design.lsp.foodmanagement.strategy.Strategy;

public class Context {

    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(Storage storage, Food food) {
        strategy.sort(storage, food);
    }
}
