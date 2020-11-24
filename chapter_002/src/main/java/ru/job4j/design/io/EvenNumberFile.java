package ru.job4j.design.io;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class EvenNumberFile {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        try (FileInputStream in = new FileInputStream("even.txt")) {
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] array = new String(text).replaceAll(System.lineSeparator()," ").split(" ");
        ToIntFunction<String> toIntFunction = Integer::parseInt;
        Function<Integer, String> func =
                e -> (e % 2) != 0 ? "The digit " + e + " is odd" : "The digit " + e + " is even";
        Arrays.stream(array).
                mapToInt(toIntFunction).
                mapToObj(func::apply).
                forEach(System.out::println);
    }
}