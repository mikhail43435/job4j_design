package ru.job4j.design.lsp.foodmanagement;

import ru.job4j.design.lsp.foodmanagement.food.Food;
import ru.job4j.design.lsp.foodmanagement.storage.Storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Создать класс обработчик перераспределения продуктов в место использования
 * ControllQuality. Класс должен перераспределять еду по хранилищам в зависимости от условий
 * (1) Если срок годности израсходован меньше чем на 25% направить в Warehouse;
 * (2) Если срок годности от 25% до 75% направить в Shop;
 * (3) Если срок годности больше 75% то выставить скидку на продукт и отправить в Shop;
 * (4) Если срок годности вышел. Отправить продукт в мусорку.
 */

public class ControlQuality {

    private final List<Storage> storeList = new ArrayList<>();

    public boolean processFood(Food food) {
        for (Storage store : storeList) {
            if (store.accept(food)) {
                store.add(food);
                return true;
            }
        }
        return false;
    }

    public void addStore(Storage... store) {
        Arrays.stream(store).forEach(e -> {
            if (!storeList.contains(e)) {
                storeList.add(e);
            }
        });
    }

    public List<Storage> getStoreList() {
        return storeList;
    }

    public void displayStorage() {
        storeList.forEach(Storage::display);
    }
}
