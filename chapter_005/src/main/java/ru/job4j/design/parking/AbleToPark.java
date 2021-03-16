package ru.job4j.design.parking;

public interface AbleToPark {

    int getNumPlacesReqForCarParking();

    int getNumPlacesReqParkForTruckParking();

    boolean isPassengerCar();
}
