package ru.job4j.design.lsp.foodmanagement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import ru.job4j.design.lsp.foodmanagement.food.Apple;
import ru.job4j.design.lsp.foodmanagement.food.Food;

import java.time.LocalDate;

public class ControlQualityTest {

    @Test
    public void testThenToShop() {
        ControlQuality cq = new ControlQuality();
        Food food =
                new Apple("New bananas",
                        LocalDate.now().plusDays(150),
                        LocalDate.now().minusDays(50),
                        100,
                        0);
        cq.processFood(food);
        food =
                new Apple("New bananas",
                        LocalDate.now().plusDays(150),
                        LocalDate.now().minusDays(50),
                        100,
                        0);
        List<Food> items = Arrays.asList(food);
        assertThat(cq.warehouse.getItemsList().size(), is(0));
        assertThat(cq.shop.getItemsList(), is(items));
        assertThat(cq.trash.getItemsList().size(), is(0));
    }

    @Test
    public void testThenToShopWithDiscount() {
        ControlQuality cq = new ControlQuality();
        Food food =
                new Apple("New bananas",
                        LocalDate.now().plusDays(49),
                        LocalDate.now().minusDays(150),
                        100,
                        0);
        cq.processFood(food);
        food =
                new Apple("New bananas",
                        LocalDate.now().plusDays(49),
                        LocalDate.now().minusDays(150),
                        100,
                        0);
        food.setDiscount(50);
        List<Food> items = Arrays.asList(food);
        assertThat(cq.warehouse.getItemsList().size(), is(0));
        assertThat(cq.shop.getItemsList(), is(items));
        assertThat(cq.trash.getItemsList().size(), is(0));
    }

    @Test
    public void testThenToWarehouse() {
        ControlQuality cq = new ControlQuality();
        Food food =
                new Apple("New bananas",
                        LocalDate.now().plusDays(463),
                        LocalDate.now().minusDays(150),
                        100,
                        0);
        cq.processFood(food);
        food =
                new Apple("New bananas",
                        LocalDate.now().plusDays(463),
                        LocalDate.now().minusDays(150),
                        100,
                        0);
        List<Food> items = Arrays.asList(food);
        assertThat(cq.warehouse.getItemsList(), is(items));
        assertThat(cq.shop.getItemsList().size(), is(0));
        assertThat(cq.trash.getItemsList().size(), is(0));
    }

    @Test
    public void testThenToTrash() {
        ControlQuality cq = new ControlQuality();
        Food food =
                new Apple("New bananas",
                        LocalDate.now().minusDays(50),
                        LocalDate.now().minusDays(150),
                        100,
                        0);
        cq.processFood(food);
        food =
                new Apple("New bananas",
                        LocalDate.now().minusDays(50),
                        LocalDate.now().minusDays(150),
                        100,
                        0);
        List<Food> items = Arrays.asList(food);
        assertThat(cq.warehouse.getItemsList().size(), is(0));
        assertThat(cq.shop.getItemsList().size(), is(0));
        assertThat(cq.trash.getItemsList(), is(items));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThenInvalidDates1() {
        ControlQuality cq = new ControlQuality();
        Food food =
                new Apple("New bananas",
                        LocalDate.now().minusDays(150),
                        LocalDate.now().minusDays(50),
                        100,
                        0);
        cq.processFood(food);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThenInvalidDates2() {
        ControlQuality cq = new ControlQuality();
        Food food =
                new Apple("New bananas",
                        LocalDate.now().plusDays(50),
                        LocalDate.now().plusDays(150),
                        100,
                        0);
        cq.processFood(food);
    }
}