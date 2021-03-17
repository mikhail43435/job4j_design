package ru.job4j.design.parking;

public abstract class Parking {

    protected int totalSpacesForCars;
    protected int totalSpacesForTrucks;
    protected int occupiedSpacesCars;
    protected int occupiedSpacesTrucks;
    protected Vehicle[] arrayOfVehiclesCars;
    protected Vehicle[] arrayOfVehiclesTrucks;

    public Parking(int numOfSpacesCars, int numOfSpacesTrucks) {
        this.totalSpacesForCars = numOfSpacesCars;
        this.totalSpacesForTrucks = numOfSpacesTrucks;
        arrayOfVehiclesCars = new Vehicle[numOfSpacesCars];
        arrayOfVehiclesCars = new Vehicle[numOfSpacesTrucks];
    }

    public boolean parkCar(Vehicle vehicle) {
        return false;
    }

    public boolean parkTruck(Vehicle vehicle) {
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

    public void display() {
    }

    public boolean isVehicleParked(Vehicle vehicle) {
        return false;
    }

}
