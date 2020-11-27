package ru.job4j.design.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) throws IllegalArgumentException {
       if (args.length == 0) {
           throw new IllegalArgumentException("Invalid value of parameter. "
                   + "Parameter show have format -KEY=VALUE");
       }
        for (String line : args) {
            String[] pair = line.split("=");
            if (pair.length < 2) {
                throw new IllegalArgumentException("Invalid value of parameter. "
                        + "Parameter show have format -KEY=VALUE");
            }
            if (!pair[0].startsWith("-") || pair[0].length() < 2) {
                throw new IllegalArgumentException("Invalid key. Key should starts with"
                        + " dash symbol and has at least one letter after");
            }
            values.put(pair[0].substring(1).trim(), pair[1].trim());
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}