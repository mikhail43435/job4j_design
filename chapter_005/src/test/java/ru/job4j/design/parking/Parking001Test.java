package ru.job4j.design.parking;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import ru.job4j.design.parking.parking.Parking;
import ru.job4j.design.parking.parking.Parking001;
import ru.job4j.design.parking.vehicle.Car;
import ru.job4j.design.parking.vehicle.Truck;
import ru.job4j.design.parking.vehicle.Vehicle;

public class Parking001Test {

    @Test
    public void testThenParkPassCarAndLeave() {
        Vehicle vehicle = new Car();
        Parking parking = new Parking001(10, 10);
        parking.parkCarToCarSide(vehicle);

        assertThat(parking.getArrayOfCarPlaces()[0], is(vehicle));
        assertThat(parking.getArrayOfTruckPlaces().length, is(0));

        parking.leaveParking(vehicle);

        assertThat(parking.getArrayOfCarPlaces().length, is(0));
        assertThat(parking.getArrayOfTruckPlaces().length, is(0));
    }

    @Test
    public void testThenParkTruckAndLeave() {
        Vehicle vehicle = new Truck(2);
        Parking parking = new Parking001(10, 10);
        parking.parkCarToTruckSide(vehicle);

        assertThat(parking.getArrayOfCarPlaces()[0], is(vehicle));
        assertThat(parking.getArrayOfCarPlaces()[1], is(vehicle));
        assertThat(parking.getArrayOfCarPlaces().length, is(1));
        assertThat(parking.getArrayOfTruckPlaces().length, is(2));

        parking.leaveParking(vehicle);

        assertThat(parking.getArrayOfCarPlaces().length, is(0));
        assertThat(parking.getArrayOfTruckPlaces().length, is(0));
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
    public void testThenParkCarSideIfFull() {
        Vehicle vehicle1 = new Car();
        Vehicle vehicle2 = new Car();
        Vehicle vehicle3 = new Car();
        Parking parking = new Parking001(2, 2);
        assertThat(parking.parkCarToTruckSide(vehicle3), is(true));
        assertThat(parking.parkCarToTruckSide(vehicle3), is(true));
        assertThat(parking.parkCarToTruckSide(vehicle3), is(false));
    }

    @Test
    public void testThenCheckIfCarSideIfFull() {
        Vehicle vehicle = new Car();
        Parking parking = new Parking001(1, 1);
        assertThat(parking.isCarSideIsFull(), is(true));
    }

    @Test
    public void testThenCheckIfTruckSideIfFull() {
        Vehicle vehicle = new Truck(2);
        Parking parking = new Parking001(1, 2);
        assertThat(parking.isTruckSideIsFull(), is(true));
    }

    @Test
    public void testThenGetFreeSpaces() {
        Vehicle vehicle1 = new Car();
        Vehicle vehicle2 = new Truck(2);
        Parking parking = new Parking001(10, 10);
        parking.parkCarToTruckSide(vehicle1);
        parking.parkCarToTruckSide(vehicle2);
        assertThat(parking.getNumOfFreeSpacesForCars(), is(99));
        assertThat(parking.getNumOfFreeSpacesForTrucks(), is(98));
    }

    @Test
    public void testThenGetOccSpaces() {
        Vehicle vehicle1 = new Car();
        Vehicle vehicle2 = new Truck(2);
        Parking parking = new Parking001(10, 10);
        parking.parkCarToTruckSide(vehicle1);
        parking.parkCarToTruckSide(vehicle2);
        assertThat(parking.getNumOfOccSpacesForCars(), is(1));
        assertThat(parking.getNumOfOccSpacesForTrucks(), is(2));
    }

    @Test
    public void testThenCheckIsVehicleParked() {
        Vehicle vehicle = new Car();
        Parking parking = new Parking001(10, 10);
        assertThat(parking.isVehicleParked(vehicle), is(true));
    }
}