package ru.job4j.design.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MyConvert {
    public static void main(String[] args) {
        MyJsonObjectCar myJsonObjectCar = new MyJsonObjectCar("Tesla", 300,
                true,
                new MyJsonObjectCarOwner("David", "45 Ocean view"),
                new Integer[]{20, 34, 45});
        MyJsonObjectCar car = myJsonObjectCar;
        Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));
        String carInString = "{\"model\":\"Tesla\",\"power\":300,\"" +
                "isValid\":true,\"owner\"" +
                ":{\"name\":\"David\",\"address\":\"45 Ocean view\"}," +
                "\"millageAtTheEndOfAYear\":[20,34,45]}";
        System.out.println(carInString);
        System.out.println(carInString.equals(gson.toJson(car)));
        MyJsonObjectCar restoredCar =
                gson.fromJson(carInString, MyJsonObjectCar.class);
        System.out.println(restoredCar);
    }
}