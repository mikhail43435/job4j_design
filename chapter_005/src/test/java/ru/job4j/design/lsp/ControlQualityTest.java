package ru.job4j.design.lsp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import ru.job4j.design.lsp.foodmanagement.ControlQuality;
import ru.job4j.design.lsp.foodmanagement.food.Apple;
import ru.job4j.design.lsp.foodmanagement.food.Bananas;
import ru.job4j.design.lsp.foodmanagement.food.Food;
import ru.job4j.design.lsp.foodmanagement.storage.*;

import java.time.LocalDate;

public class ControlQualityTest {

    @Test
    public void testThenToShop() {
        Storage shop = new Shop();
        Storage warehouse = new Warehouse();
        Storage trash = new Trash();

        ControlQuality cq = new ControlQuality();
        cq.addStore(shop, warehouse, trash);
        Food food =
                new Apple("Red apple",
                        LocalDate.now().plusDays(150),
                        LocalDate.now().minusDays(50),
                        100,
                        0);
        Food foodWithDiscount =
                new Bananas("New bananas",
                        LocalDate.now().plusDays(49),
                        LocalDate.now().minusDays(150),
                        100,
                        0);
        assertThat(cq.processFood(food), is(true));
        assertThat(cq.processFood(foodWithDiscount), is(true));
        Food foodWithDiscountSet =
                new Bananas("New bananas",
                        LocalDate.now().plusDays(49),
                        LocalDate.now().minusDays(150),
                        100,
                        50);
        List<Food> items = Arrays.asList(food, foodWithDiscountSet);
        //cq.displayStorage();
        assertThat(warehouse.getItemsList().size(), is(0));
        assertThat(shop.getItemsList(), is(items));
        assertThat(trash.getItemsList().size(), is(0));
    }

    @Test
    public void testThenToWarehouse() {
        Storage shop = new Shop();
        Storage warehouse = new Warehouse();
        Storage trash = new Trash();

        ControlQuality cq = new ControlQuality();
        cq.addStore(shop, warehouse, trash);
        Food food =
                new Apple("Red apple",
                        LocalDate.now().plusDays(463),
                        LocalDate.now().minusDays(150),
                        100,
                        0);
        assertThat(cq.processFood(food), is(true));
        List<Food> items = Arrays.asList(food);
        //cq.displayStorage();
        assertThat(warehouse.getItemsList(), is(items));
        assertThat(shop.getItemsList().size(), is(0));
        assertThat(trash.getItemsList().size(), is(0));
    }

    @Test
    public void testThenToTrash() {
        Storage shop = new Shop();
        Storage warehouse = new Warehouse();
        Storage trash = new Trash();

        ControlQuality cq = new ControlQuality();
        cq.addStore(shop, warehouse, trash);
        Food food =
                new Apple("Red apple",
                        LocalDate.now().minusDays(50),
                        LocalDate.now().minusDays(150),
                        100,
                        0);
        assertThat(cq.processFood(food), is(true));
        List<Food> items = Arrays.asList(food);
        //cq.displayStorage();
        assertThat(warehouse.getItemsList().size(), is(0));
        assertThat(shop.getItemsList().size(), is(0));
        assertThat(trash.getItemsList(), is(items));
    }

    @Test
    public void testThenResort() {
        Storage shop = new Shop();
        Storage warehouse = new Warehouse();
        Storage trash = new Trash();

        ControlQuality cq = new ControlQuality();
        cq.addStore(shop, warehouse, trash);
        Food food =
                new Apple("Red apple",
                        LocalDate.now().plusDays(150),
                        LocalDate.now().minusDays(50),
                        100,
                        0);
        Food foodWithDiscount =
                new Bananas("New bananas",
                        LocalDate.now().plusDays(49),
                        LocalDate.now().minusDays(150),
                        100,
                        0);
        assertThat(cq.processFood(food), is(true));
        assertThat(cq.processFood(foodWithDiscount), is(true));
        Food foodWithDiscountSet =
                new Bananas("New bananas",
                        LocalDate.now().plusDays(49),
                        LocalDate.now().minusDays(150),
                        100,
                        50);
        List<Food> items = Arrays.asList(food, foodWithDiscountSet);
        //cq.displayStorage();
        assertThat(warehouse.getItemsList().size(), is(0));
        assertThat(shop.getItemsList(), is(items));
        assertThat(trash.getItemsList().size(), is(0));
        cq.resort();
        assertThat(warehouse.getItemsList().size(), is(0));
        assertThat(shop.getItemsList(), is(items));
        assertThat(trash.getItemsList().size(), is(0));
    }
}