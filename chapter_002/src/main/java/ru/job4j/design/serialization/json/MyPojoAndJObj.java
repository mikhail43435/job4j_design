package ru.job4j.design.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.design.serialization.json.educase.Contact;
import ru.job4j.design.serialization.json.educase.Person;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MyPojoAndJObj {

    public static void main(String[] args) {
        /* JSONObject напрямую методом put */

        final MyJsonObjectCar car = new MyJsonObjectCar("Tesla", 300,
                true,
                new MyJsonObjectCarOwner("David", "45 Ocean view"),
                new Integer[]{20, 34, 45});

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("model", car.getModel());
        jsonObject.put("power", car.getPower());
        jsonObject.put("isValid", car.isValid());
        jsonObject.put("owner", car.getOwner());
        jsonObject.put("millageAtTheEndOfAYear", car.getMillageAtTheEndOfAYear());

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(car).toString());
    }
}