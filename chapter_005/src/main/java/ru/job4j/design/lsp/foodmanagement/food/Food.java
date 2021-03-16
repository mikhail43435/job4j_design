package ru.job4j.design.lsp.foodmanagement.food;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Food {
    private String name;
    private LocalDate expiryDate;
    private LocalDate createDate;
    private int price;
    private int discount;

    protected Food(String name, LocalDate expiryDate, LocalDate createDate, int price, int discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getexpiryDate() {
        return expiryDate;
    }

    public void setexpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getcreateDate() {
        return createDate;
    }

    public void setcreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", expiryDate=" + expiryDate +
                ", createDate=" + createDate +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return price == food.price &&
                discount == food.discount &&
                Objects.equals(name, food.name) &&
                Objects.equals(expiryDate, food.expiryDate) &&
                Objects.equals(createDate, food.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expiryDate, createDate, price, discount);
    }
}
