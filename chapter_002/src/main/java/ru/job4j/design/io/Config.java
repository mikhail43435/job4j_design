package ru.job4j.design.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.function.BiFunction;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        BiFunction<String, Integer, String> putter = (string, code) ->
                code == 0 ? string.split("=")[0] : string.split("=")[1];
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            in.lines().
                    filter(e -> (!e.isEmpty() && !e.startsWith("##") && e.contains("="))).
                    forEach(e -> values.put(putter.apply(e, 0), putter.apply(e, 1)));
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