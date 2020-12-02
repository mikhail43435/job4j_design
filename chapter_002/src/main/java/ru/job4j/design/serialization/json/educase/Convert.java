package ru.job4j.design.serialization.json.educase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Convert {
    public static void main(String[] args) {
        /* JSONObject из json-строки строки */
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person.isSex());
        jsonObject.put("age", person.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(person).toString());



/*        final Person person = new Person(false, 30,
                new Contact("11-111"), "Worker", "Married");

        *//* Преобразуем объект person в json-строку. *//*
        final Gson gson = new GsonBuilder().create();
        System.out.println("json-строка представления объекта");
        System.out.println(gson.toJson(person));

        *//* Модифицируем json-строку *//*
        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";
        System.out.println("Строка personJson");
        System.out.println(personJson);
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println("Модифицированная json-строка представления объекта");
        System.out.println(personMod);*/
    }
}
