package ru.job4j.design.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.lang.IllegalArgumentException;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() throws IllegalArgumentException {
        Function<String, String[]> splitter = (string -> {
            String[] array = string.split("=");
            if (array.length != 2) {
                throw new IllegalArgumentException();
            }
            return array;
        }
        );
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            in.lines().
                    filter(e -> (!e.isEmpty() && !e.startsWith("#") && e.contains("="))).
                    forEach(e -> values.put(splitter.apply(e)[0], splitter.apply(e)[1]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        //throw new UnsupportedOperationException("Don't impl this method yet!");
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}