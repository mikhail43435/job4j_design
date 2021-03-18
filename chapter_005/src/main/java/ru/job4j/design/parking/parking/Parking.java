package ru.job4j.design.parking.parking;

import ru.job4j.design.parking.vehicle.Vehicle;

public abstract class Parking {

    private final int totalSpacesForCars;
    private final int totalSpacesForTrucks;
    private int occupiedSpacesCars;
    private int occupiedSpacesTrucks;
    private Vehicle[] arrayOfCarPlaces;
    private Vehicle[] arrayOfVehiclesTrucks;

    public Parking(int numOfSpacesCars, int numOfSpacesTrucks) {
        this.totalSpacesForCars = numOfSpacesCars;
        this.totalSpacesForTrucks = numOfSpacesTrucks;
        arrayOfCarPlaces = new Vehicle[numOfSpacesCars];
        arrayOfCarPlaces = new Vehicle[numOfSpacesTrucks];
    }

    public boolean parkCarToCarSide(Vehicle vehicle) {
        return false;
    }

    public boolean parkCarToTruckSide(Vehicle vehicle) {
        return false;
    }

    public boolean leaveParking(Vehicle vehicle) {
        return false;
    }

    public int getNumOfFreeSpacesForCars() {
        return totalSpacesForCars - occupiedSpacesCars;
    }

    public int getNumOfOccSpacesForCars() {
        return occupiedSpacesCars;
    }

    public int getNumOfFreeSpacesForTrucks() {
        return totalSpacesForTrucks - occupiedSpacesTrucks;
    }

    public int getNumOfOccSpacesForTrucks() {
        return occupiedSpacesTrucks;
    }

    public Vehicle[] getArrayOfCarPlaces() {
        return null;
    }

    public Vehicle[] getArrayOfTruckPlaces() {
        return null;
    }

    public boolean isCarSideIsFull() {
        return false;
    }

    public boolean isTruckSideIsFull() {
        return false;
    }

    public void display() {
    }

    public boolean isVehicleParked(Vehicle vehicle) {
        return false;
    }
}
