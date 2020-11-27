package ru.job4j.design.io;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class SearchDub {
    public static void main(String[] args) throws IOException {
        File startFolder = null;
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new java.io.File(".")); //
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            startFolder = fc.getSelectedFile();
        }
        //if (startFolder == null) return;
        List<Path> pathList;
        Set<Path> dubPathSet = new HashSet<>();
        HashMap<FileData, Path> hashTable = new HashMap<>();
        pathList = search(startFolder.toPath(), "");
        for (Path path : pathList) {
            FileData file = new FileData(path.toFile().getName(), path.toFile().length());
            if (hashTable.containsKey(file)) {
                dubPathSet.add(path);
                dubPathSet.add(hashTable.get(file));
            } else {
                hashTable.put(file, path);
            }
        }
        List<Path> dubPathList = new ArrayList<>(dubPathSet);
        Comparator<Path> cmp = (o1, o2) -> o1.toFile().getName().compareTo(o2.toFile().getName());
        dubPathList.sort(cmp);
        Iterator<Path> it = dubPathList.iterator();
        Path currPath;
        Path lastPath = null;
        while (it.hasNext()) {
            currPath = it.next();
            if (lastPath == null
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
        System.out.println(dubPathSet.size());
    }

    public static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles seacher = new SearchFiles(path -> path.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, seacher);
        return seacher.getPaths();
    }

    private static class FileData {
        String name;
        long size;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FileData fileData = (FileData) o;
            return size == fileData.size &&
                    Objects.equals(name, fileData.name);
        }

        @Override
        public String toString() {
            return "FileData{" +
                    "name='" + name + '\'' +
                    ", size=" + size +
                    '}';
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, size);
        }

        public FileData(String name, long size) {
            this.name = name;
            this.size = size;
        }
    }
}