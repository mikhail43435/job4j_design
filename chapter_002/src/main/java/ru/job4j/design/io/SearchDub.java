package ru.job4j.design.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

import static java.util.Objects.isNull;
import static ru.job4j.design.io.DirChooseDialog.chooseDir;

public class SearchDub {
    public static void main(String[] args) throws IOException {
        File startFolder = chooseDir();
        if (isNull(startFolder)) {
            System.out.println("Каталог не выбран");
            return;
        }
        Instant start = Instant.now();
        System.out.println("Поиск дубликатов в папке: " + startFolder.toString());
        //=====================================
        /*List<Path> pathList;
        Set<Path> dubPathSet = new HashSet<>();
        HashMap<FileData, Path> hashTable = new HashMap<>();*/
        ArrayList<Path> dubPathList = search(startFolder.toPath(), "");
        //System.out.println(dubPathList);
/*
        for (Path path : pathList) {
            FileData file = new FileData(path.toFile().getName(), path.toFile().length());
            if (hashTable.containsKey(file)) {
                dubPathSet.add(path);
                dubPathSet.add(hashTable.get(file));
            } else {
                hashTable.put(file, path);
            }
        }
*/
        //List<Path> dubPathList = new ArrayList<>(dubPathSet);
        //=====================================
        Comparator<Path> cmp = Comparator.comparing(o -> o.toFile().getName());
        dubPathList.sort(cmp);
        Iterator<Path> it = dubPathList.iterator();
        Path currPath;
        Path lastPath = null;
        while (it.hasNext()) {
            currPath = it.next();
            if (isNull(lastPath)
                    || (!currPath.toFile().getName().equals(lastPath.toFile().getName())
                    && currPath.toFile().length() != lastPath.toFile().length())) {
                System.out.println();
                System.out.println("file/size :   "
                        + currPath.toFile().getName()
                        + "   "
                        + currPath.toFile().length()
                        + " b");
            }
            System.out.println("path:   " + currPath.toFile().getPath());
            lastPath = currPath;
        }
        System.out.println(dubPathList.size());
        Instant finish = Instant.now();
        System.out.println("На выполнение поиска было потрачено "
                + (Duration.between(start, finish).toSeconds())
                + " секунд");
    }

    public static ArrayList<Path> search(Path root, String ext) throws IOException {
        SearchFilesDub searcher = new SearchFilesDub(path -> path.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}