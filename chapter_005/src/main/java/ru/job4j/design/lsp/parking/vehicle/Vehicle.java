package ru.job4j.design.lsp.parking.vehicle;

public abstract class Vehicle implements AbleToPark {

    protected int placesRequiredForPark;

    protected Vehicle(int placesRequiredForPark) {
        this.placesRequiredForPark = placesRequiredForPark;
    }
}