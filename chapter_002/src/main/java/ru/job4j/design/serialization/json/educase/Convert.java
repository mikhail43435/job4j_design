package ru.job4j.design.serialization.json.educase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Convert {
    public static void main(String[] args) {
        final Person person = new Person(false, 30,
                new Contact("11-111"), "Worker", "Married");

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println("json-строка представления объекта");
        System.out.println(gson.toJson(person));

        /* Модифицируем json-строку */
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
        System.out.println(personMod);
    }
}
