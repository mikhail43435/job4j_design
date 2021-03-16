package ru.job4j.design.lsp.foodmanagement.food;

import java.time.LocalDate;

public class Bananas extends Food {
    public Bananas(String name, LocalDate expiryDate, LocalDate createDate, int price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
