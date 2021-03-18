package ru.job4j.design.parking.vehicle;

public class Car extends Vehicle {

    public Car() {
        super(1);
    }

    @Override
    public int getNumPlacesReqForParking() {
        return placesRequiredForPark;
    }
}
