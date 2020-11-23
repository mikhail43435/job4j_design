package ru.job4j.design.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class LogFilter {

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        log.forEach(System.out::println);
        save(log, "404.txt");
    }

    public static List<String> filter(String file) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
                in.lines().forEach(lines::add);
        } catch (Exception e) {
                e.printStackTrace();
        }
        Predicate<String[]> check = e -> (e[e.length - 2].equals("404")
                && isDigit(e[e.length - 1]));
        lines.removeIf(s -> !check.test(s.split(" ")));
        return lines;
    }

    public static void save(List<String> log, String file) {
            try (PrintWriter out = new PrintWriter(
                    new BufferedOutputStream(new FileOutputStream(file)))) {
                Consumer<String> cons = e -> out.write(e + System.getProperty("line.separator"));
                log.forEach(cons);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}