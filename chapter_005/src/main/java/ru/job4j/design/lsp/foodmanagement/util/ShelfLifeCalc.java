package ru.job4j.design.lsp.foodmanagement.util;

import ru.job4j.design.lsp.foodmanagement.food.Food;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class ShelfLifeCalc {

    private ShelfLifeCalc() {
        throw new IllegalStateException("Utility class");
    }

    public static int getPercentageOfShelfLifeLeft(Food food) {
        if (food.getexpiryDate().isBefore(food.getcreateDate())) return 0;
        long daysOfShelfLife = DAYS.between(food.getcreateDate(), food.getexpiryDate());
        long daysLeft = DAYS.between(LocalDate.now(), food.getexpiryDate());
        return (int) (1000  - (daysLeft * 1000) / daysOfShelfLife);
    }
}