package ru.job4j.design.lsp.parking.parking;

import ru.job4j.design.lsp.parking.vehicle.Vehicle;

import java.util.Arrays;
import java.util.function.Consumer;

import static java.util.Objects.isNull;

public abstract class Parking {

    private final int totalSpacesForCars;
    private final int totalSpacesForTrucks;
    private int occupiedSpacesCars;
    private int occupiedSpacesTrucks;
    private final Vehicle[] arrayOfCarPlaces;
    private final Vehicle[] arrayOfTrucksPlaces;

    public Parking(int numOfSpacesCars, int numOfSpacesTrucks) {
        this.totalSpacesForCars = numOfSpacesCars;
        this.totalSpacesForTrucks = numOfSpacesTrucks;
        arrayOfCarPlaces = new Vehicle[numOfSpacesCars];
        arrayOfTrucksPlaces = new Vehicle[numOfSpacesTrucks];
    }

    public boolean parkCarToCarSide(Vehicle vehicle) {
        if (vehicle.getNumPlacesReqForParking() > getNumOfFreeSpacesForCars()) return false;
        return parkVehicle(arrayOfCarPlaces, vehicle, e -> occupiedSpacesCars++);
    }

    public boolean parkCarToTruckSide(Vehicle vehicle) {
        if (vehicle.getNumPlacesReqForParking() == 1
                || vehicle.getNumPlacesReqForParking() > getNumOfFreeSpacesForTrucks()) {
            return false;
        }
        return parkVehicle(arrayOfTrucksPlaces, vehicle, e -> occupiedSpacesTrucks++);
    }

    private boolean parkVehicle(Vehicle[] array, Vehicle vehicle, Consumer<Integer> consumer) {
        if (isVehicleParked(vehicle)) return false;
        int startPlace = findPlacesNextToEachOther(array, vehicle.getNumPlacesReqForParking());
        if (startPlace == -1) return false;
        for (int index = startPlace; index < startPlace + vehicle.getNumPlacesReqForParking(); index++) {
            array[index] = vehicle;
            consumer.accept(0);
        }
        return true;
    }

    private int findPlacesNextToEachOther(Vehicle[] array, int reqPlaces) {
        int counter = 0;
        for (int index = 0; index < array.length; index++) {
            if (isNull(array[index])) {
                counter++;
            } else {
                counter = 0;
            }
            if (counter == reqPlaces) {
                return index - (reqPlaces - 1);
            }
        }
        return -1;
    }

    public boolean leaveParking(Vehicle vehicle) {
        if (!isVehicleParked(vehicle)) return false;
        int[] array1 = getPlacesOfParkedVehicle(arrayOfCarPlaces, vehicle);
        if (array1.length != 0) {
            for (int index = 0; index < array1.length; index++) {
                arrayOfCarPlaces[array1[0]] = null;
                occupiedSpacesCars--;
            }
            return true;
        }
        int[] array2 = getPlacesOfParkedVehicle(arrayOfTrucksPlaces, vehicle);
        if (array2.length != 0) {
            for (int index = 0; index < array2.length; index++) {
                arrayOfTrucksPlaces[array2[index]] = null;
                occupiedSpacesTrucks--;
            }
            return true;
        }
        return false;
    }

    private int[] getPlacesOfParkedVehicle(Vehicle[] arrayOfVehicles, Vehicle vehicle) {
        int position = -1;
        for (int index = 0; index < arrayOfVehicles.length; index++) {
            if (arrayOfVehicles[index] == vehicle) {
                position = index;
                break;
            }
        }
        if (position == -1) {
            return new int[0];
        } else {
            int[] result = new int[vehicle.getNumPlacesReqForParking()];
            for (int indext = 0; indext < result.length; indext++) {
                result[indext] = position++;
            }
            return result;
        }
    }

    public boolean isVehicleParked(Vehicle vehicle) {
        return Arrays.stream(arrayOfCarPlaces).anyMatch(e -> e == vehicle)
                || Arrays.stream(arrayOfTrucksPlaces).anyMatch(e -> e == vehicle);
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
        return arrayOfCarPlaces;
    }

    public Vehicle[] getArrayOfTruckPlaces() {
        return arrayOfTrucksPlaces;
    }

    public boolean isCarSideIsFull() {
        return arrayOfCarPlaces.length == occupiedSpacesCars;
    }

    public boolean isTruckSideIsFull() {
        return arrayOfTrucksPlaces.length == occupiedSpacesTrucks;
    }

    public void display() {
        System.out.println("=== Printing list of car side ===");
        Arrays.stream(arrayOfCarPlaces).forEach(System.out::println);
        System.out.println("=== Printing list of truck side ===");
        Arrays.stream(arrayOfTrucksPlaces).forEach(System.out::println);
        System.out.println("=== end of print ===");
    }
}
