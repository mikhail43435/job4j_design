package ru.job4j.design.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.util.Objects.isNull;

public class SearchFilesDub extends SearchFiles {
    private final Predicate<Path> pred;
    //private final List<Path> pathList = new ArrayList<>();
    private List<Path> pathList;
    private final Set<Path> dubPathSet = new HashSet<>();
    private final HashMap<FileData, Path> hashTable = new HashMap<>();

    public SearchFilesDub(Predicate<Path> pred) {
        super(pred);
        this.pred = pred;
    }

    public ArrayList<Path> getPaths() {
        return new ArrayList<Path>(dubPathSet);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!pred.test(file)) {
            return CONTINUE;
        }
        FileData newFile = new FileData(file.toFile().getName(), file.toFile().length());
        if (hashTable.containsKey(newFile)) {
            dubPathSet.add(file);
            dubPathSet.add(hashTable.get(newFile));
        } else {
            hashTable.put(newFile, file);
        }
        return CONTINUE;
    }
}