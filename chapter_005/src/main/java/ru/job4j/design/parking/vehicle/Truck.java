package ru.job4j.design.parking.vehicle;

public class Truck extends Vehicle {

    public Truck(int placesRequiredForPark) {
        super(placesRequiredForPark);
        if (placesRequiredForPark == 1) {
            throw new IllegalArgumentException("Truck can's has 1 size park place");
        }
    }

    @Override
    public int getNumPlacesReqForParking() {
        return placesRequiredForPark;
    }
}
