package ru.job4j.design.serialization.json;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class MyJsonObjectCar {
    private final String model;
    private final Integer power;
    private final boolean isValid;
    private final MyJsonObjectCarOwner owner;
    //@SerializedName("mill")
    private final Integer[] millageAtTheEndOfAYear;

    public MyJsonObjectCar(String model, Integer power,
                           boolean isValid, MyJsonObjectCarOwner owner,
                           Integer[] millageAtTheEndOfAYear) {
        this.model = model;
        this.power = power;
        this.isValid = isValid;
        this.owner = owner;
        this.millageAtTheEndOfAYear = millageAtTheEndOfAYear;
    }

    @Override
    public String toString() {
        return "Overrided toString" +
                "MyJsonObjectCar{" +
                "model='" + model + '\'' +
                ", power=" + power +
                ", isValid=" + isValid +
                ", owner=" + owner +
                ", millageAtTheEndOfAYear=" + Arrays.toString(millageAtTheEndOfAYear) +
                '}';
    }
}
