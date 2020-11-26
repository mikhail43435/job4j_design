package ru.job4j.design.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Search {
    public static void main(String[] args) throws IOException {
        //search(Paths.get("."), "java").forEach(System.out::println);
        search(Paths.get("."), "java").forEach(System.out::println);
    }

    public static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles seacher = new SearchFiles(path -> path.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, seacher);
        return seacher.getPaths();
    }

    private static class SearchFiles implements FileVisitor<Path> {
        private final Predicate<Path> pred;
        private final List<Path> pathList = new ArrayList<>();

        public SearchFiles(Predicate<Path> pred) {
            this.pred = pred;
        }

        public List<Path> getPaths() {
            return pathList;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir,
                                                 BasicFileAttributes attrs) throws IOException {
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (pred.test(file)) {
                pathList.add(file);
            }
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            return CONTINUE;
        }
    }
}