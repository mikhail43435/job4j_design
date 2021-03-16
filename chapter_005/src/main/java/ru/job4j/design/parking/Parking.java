package ru.job4j.design.parking;

public abstract class Parking {

    protected int totalSpaces;
    protected int occupiedSpaces;
    protected AbleToPark[] arrayOfVehicles;

    public Parking(int numOfSpaces) {
        this.totalSpaces = numOfSpaces;
        arrayOfVehicles = new AbleToPark[numOfSpaces];
    }

    public boolean toPark(AbleToPark vehicle){
        return false;
    }

    public boolean leaveParking(AbleToPark vehicle){
        return false;
    }

    public int getNumOfFreeSpaces() {
        return totalSpaces - occupiedSpaces;
    }

    public int getNumOfOccSpaces() {
        return occupiedSpaces;
    }

    public int getTotalSpaces() {
        return totalSpaces;
    }
}
