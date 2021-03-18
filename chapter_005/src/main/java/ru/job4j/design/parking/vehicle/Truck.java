package ru.job4j.design.parking.vehicle;

public class Truck extends Vehicle {

    public Truck(int placesRequiredForPark) {
        super(placesRequiredForPark);
    }

    @Override
    public int getNumPlacesReqForParking() {
        return placesRequiredForPark;
    }
}
