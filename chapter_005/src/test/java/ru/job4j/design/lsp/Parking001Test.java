package ru.job4j.design.lsp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import ru.job4j.design.lsp.parking.parking.Parking;
import ru.job4j.design.lsp.parking.vehicle.Car;
import ru.job4j.design.lsp.parking.vehicle.Vehicle;
import ru.job4j.design.lsp.parking.parking.Parking001;
import ru.job4j.design.lsp.parking.vehicle.Truck;

public class Parking001Test {

    @Test
    public void testThenParkCarAndLeave() {
        Vehicle vehicle = new Car();
        Parking parking = new Parking001(10, 10);
        parking.parkCarToCarSide(vehicle);
        assertThat(parking.getArrayOfCarPlaces()[0], is(vehicle));
        assertThat(parking.getNumOfFreeSpacesForTrucks(), is(10));
        parking.leaveParking(vehicle);
        assertThat(parking.getNumOfFreeSpacesForCars(), is(10));
        assertThat(parking.getNumOfFreeSpacesForTrucks(), is(10));
    }

    @Test
    public void testThenParkTruckToTruckSideAndLeave() {
        Vehicle vehicle = new Truck(2);
        Parking parking = new Parking001(10, 10);
        parking.parkCarToTruckSide(vehicle);
        assertThat(parking.getNumOfFreeSpacesForCars(), is(10));
        assertThat(parking.getArrayOfTruckPlaces()[0], is(vehicle));
        parking.leaveParking(vehicle);
        assertThat(parking.getNumOfFreeSpacesForCars(), is(10));
        assertThat(parking.getNumOfFreeSpacesForTrucks(), is(10));
    }

    @Test
    public void testThenTryParkCarToTruckSide() {
        Vehicle vehicle = new Car();
        Parking parking = new Parking001(2, 2);
        assertThat(parking.parkCarToTruckSide(vehicle), is(false));
    }

    @Test
    public void testThenTryParkTruckToCarSide() {
        Vehicle vehicle = new Truck(3);
        Parking parking = new Parking001(10, 10);
        assertThat(parking.parkCarToTruckSide(vehicle), is(true));
    }

    @Test
    public void testThenTryParkTruckToFullCarSide() {
        Vehicle vehicle = new Truck(3);
        Parking parking = new Parking001(2, 2);
        assertThat(parking.parkCarToTruckSide(vehicle), is(false));
    }
    
    @Test
    public void testThenParkTruckSideIfFull() {
        Vehicle vehicle1 = new Truck(2);
        Vehicle vehicle2 = new Truck(2);
        Parking parking = new Parking001(2, 2);
        assertThat(parking.parkCarToTruckSide(vehicle1), is(true));
        assertThat(parking.parkCarToTruckSide(vehicle2), is(false));
    }

    @Test
    public void testThenTryParkToParkToCarSideAndNoSpaceNextToEachOther() {
        Vehicle vehicle1 = new Car();
        Vehicle vehicle2 = new Car();
        Vehicle vehicle3 = new Truck(3);
        Parking parking = new Parking001(4, 1);
        parking.parkCarToCarSide(vehicle1);
        parking.parkCarToCarSide(vehicle2);
        parking.leaveParking(vehicle1);
        assertThat(parking.parkCarToCarSide(vehicle3), is(false));
    }

    @Test
    public void testThenTryParkToParkToTruckSideAndNoSpaceNextToEachOther() {
        Vehicle vehicle1 = new Truck(2);
        Vehicle vehicle2 = new Truck(2);
        Vehicle vehicle3 = new Truck(3);
        Parking parking = new Parking001(0, 6);
        parking.parkCarToTruckSide(vehicle1);
        parking.parkCarToTruckSide(vehicle2);
        parking.leaveParking(vehicle1);
        assertThat(parking.parkCarToCarSide(vehicle3), is(false));
    }

    @Test
    public void testThenTryParkToCarSideToTheLastSlot() {
        Vehicle vehicle1 = new Car();
        Vehicle vehicle2 = new Car();
        Vehicle vehicle3 = new Car();
        Parking parking = new Parking001(3, 0);
        parking.parkCarToTruckSide(vehicle1);
        parking.parkCarToTruckSide(vehicle2);
        assertThat(parking.parkCarToCarSide(vehicle3), is(true));
    }

    @Test
    public void testThenTryParkToTruckSideToTheLastSlot() {
        Vehicle vehicle1 = new Truck(2);
        Vehicle vehicle2 = new Truck(2);
        Vehicle vehicle3 = new Truck(2);
        Parking parking = new Parking001(0, 6);
        parking.parkCarToTruckSide(vehicle1);
        parking.parkCarToTruckSide(vehicle2);
        assertThat(parking.parkCarToTruckSide(vehicle3), is(true));
    }

    @Test
    public void testThenParkCarSideIfFull() {
        Vehicle vehicle1 = new Car();
        Vehicle vehicle2 = new Truck(2);
        Vehicle vehicle3 = new Truck(2);
        Parking parking = new Parking001(2, 2);
        assertThat(parking.parkCarToCarSide(vehicle1), is(true));
        assertThat(parking.parkCarToTruckSide(vehicle2), is(true));
        assertThat(parking.parkCarToCarSide(vehicle3), is(false));
        assertThat(parking.parkCarToTruckSide(vehicle3), is(false));
    }

    @Test
    public void testThenCheckIfCarSideIsEmptyThenIsFull() {
        Vehicle vehicle = new Car();
        Parking parking = new Parking001(1, 1);
        assertThat(parking.isCarSideIsFull(), is(false));
        parking.parkCarToCarSide(vehicle);
        assertThat(parking.isCarSideIsFull(), is(true));
    }

    @Test
    public void testThenCheckIfTruckSideIsEmptyThenIsFull() {
        Vehicle vehicle = new Truck(2);
        Parking parking = new Parking001(1, 2);
        assertThat(parking.isTruckSideIsFull(), is(false));
        parking.parkCarToTruckSide(vehicle);
        assertThat(parking.isTruckSideIsFull(), is(true));
    }

    @Test
    public void testThenGetFreeSpaces() {
        Vehicle vehicle1 = new Car();
        Vehicle vehicle2 = new Truck(2);
        Parking parking = new Parking001(10, 10);
        parking.parkCarToCarSide(vehicle1);
        parking.parkCarToTruckSide(vehicle2);
        assertThat(parking.getNumOfFreeSpacesForCars(), is(9));
        assertThat(parking.getNumOfFreeSpacesForTrucks(), is(8));
    }

    @Test
    public void testThenGetOccSpaces() {
        Vehicle vehicle1 = new Car();
        Vehicle vehicle2 = new Truck(2);
        Parking parking = new Parking001(10, 10);
        parking.parkCarToCarSide(vehicle1);
        parking.parkCarToTruckSide(vehicle2);
        assertThat(parking.getNumOfOccSpacesForCars(), is(1));
        assertThat(parking.getNumOfOccSpacesForTrucks(), is(2));
    }

    @Test
    public void testThenCheckIsVehicleParkedToCarSide() {
        Vehicle vehicle = new Car();
        Parking parking = new Parking001(10, 10);
        parking.parkCarToCarSide(vehicle);
        assertThat(parking.isVehicleParked(vehicle), is(true));
    }

    @Test
    public void testThenCheckIsVehicleParkedToTruckSide() {
        Vehicle vehicle = new Truck(2);
        Parking parking = new Parking001(10, 10);
        parking.parkCarToTruckSide(vehicle);
        assertThat(parking.isVehicleParked(vehicle), is(true));
    }

    @Test
    public void testThenTryParkToNullParking() {
        Vehicle vehicle = new Truck(2);
        Parking parking = new Parking001(0, 0);
        assertThat(parking.parkCarToCarSide(vehicle), is(false));
        assertThat(parking.parkCarToTruckSide(vehicle), is(false));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTryToCreate1SizeParkPlaceTruck() {
        new Truck(1);
    }
}