package ru.job4j.design.io;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class Search {
    public static void main(String[] args) throws IOException {
        search(Paths.get("."), "java").forEach(System.out::println);
    }

    public static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles seacher = new SearchFiles(path -> path.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, seacher);
        return seacher.getPaths();
    }
}