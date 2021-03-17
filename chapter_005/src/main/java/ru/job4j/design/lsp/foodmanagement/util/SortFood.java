package ru.job4j.design.lsp.foodmanagement.util;

import ru.job4j.design.lsp.foodmanagement.food.Food;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public final class SortFood {

    private SortFood() {
        throw new IllegalStateException("Utility class");
    }

    public static int getFoodGroupByDate(Food food) {
        if (food.getexpiryDate().isBefore(food.getcreateDate())) return 0;
        long daysOfShelfLife = DAYS.between(food.getcreateDate(), food.getexpiryDate());
        long daysLeft = DAYS.between(LocalDate.now(), food.getexpiryDate());
        long ratio = 1000 - (daysLeft * 1000) / daysOfShelfLife;
        //System.out.println(daysOfShelfLife + " / " + daysLeft + " / " + ratio);
        if (ratio < 0) {
            return 0;
        } else if (ratio < 250) {
            return 1;
        } else if (ratio <= 750) {
            return 2;
        } else if (ratio <= 1000) {
            return 3;
        } else {
            return 4;
        }
    }
}