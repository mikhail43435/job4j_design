package ru.job4j.design.io;

import java.io.FileOutputStream;
import java.util.stream.Stream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            String lineSeparator = System.getProperty("line.separator");
            out.write("Multiplication table".getBytes());
            out.write(lineSeparator.getBytes());
            for (int i = 1; i <= 10; i++) {
                for (int j = 1; j <= 10; j++) {
                    String text = i + " * " + j + " = " + (i * j) + lineSeparator;
                    out.write(text.getBytes());
                }
                out.write(lineSeparator.getBytes());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}