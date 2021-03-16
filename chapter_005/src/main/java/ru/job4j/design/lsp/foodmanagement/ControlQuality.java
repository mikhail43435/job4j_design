package ru.job4j.design.lsp.foodmanagement;

import ru.job4j.design.lsp.foodmanagement.food.Food;
import ru.job4j.design.lsp.foodmanagement.storage.Shop;
import ru.job4j.design.lsp.foodmanagement.storage.Storage;
import ru.job4j.design.lsp.foodmanagement.storage.Trash;
import ru.job4j.design.lsp.foodmanagement.storage.Warehouse;
import ru.job4j.design.lsp.foodmanagement.strategy.ConcreteStrategyMoveToStore;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Создать класс обработчик перераспределения продуктов в место использования
 * ControllQuality. Класс должен перераспределять еду по хранилищам в зависимости от условий
 *      (1) Если срок годности израсходован меньше чем на 25% направить в Warehouse;
 *      (2) Если срок годности от 25% до 75% направить в Shop;
 *      (3) Если срок годности больше 75% то выставить скидку на продукт и отправить в Shop;
 *      (4) Если срок годности вышел. Отправить продукт в мусорку.
 */

public class ControlQuality {
    public final Storage shop = new Shop();
    public final Storage warehouse = new Warehouse();
    public final Storage trash = new Trash();
    public final Context context = new Context();

    public void processFood(Food food) {
        context.setStrategy(new ConcreteStrategyMoveToStore());
        Storage unit;
        switch (selectFoodGroupByDate(food, LocalDate.now())) {
            case 1 -> unit = warehouse;
            case 2 -> unit = shop;
            case 3 -> {
                food.setDiscount(getDiscount(food));
                unit = shop;
            }
            case 4 -> unit = trash;
            default -> throw new IllegalArgumentException("Invalid food group for food: " + food);
        }
        context.executeStrategy(unit, food);
    }

    private int selectFoodGroupByDate(Food food, LocalDate nowDate) {
        if (food.getexpiryDate().isBefore(food.getcreateDate())) return 0;
        long daysOfShelfLife = DAYS.between(food.getcreateDate(), food.getexpiryDate());
        long daysLeft = DAYS.between(nowDate, food.getexpiryDate());
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

    private int getDiscount(Food food) {
        return 50;
    }
}
