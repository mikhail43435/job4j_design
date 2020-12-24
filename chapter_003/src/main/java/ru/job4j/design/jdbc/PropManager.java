package ru.job4j.design.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Статический класс, загружает и выдает ключи из файла app.properties
 */
public class PropManager {
    private final Properties prop = new Properties();

    /**
     * Конструктор класса
     */
    public PropManager(String propName) {
        try (InputStream inputStream = PropManager.class
                .getClassLoader().getResourceAsStream(propName)) {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Выдает значение по переданному ключу
     * @param key ключ искомого параметра
     * @return возвращает значение по ключу, null если переданный ключ отсутствует
     */
    public String getKeyValue(String key) {
        return prop.getProperty(key);
    }
}