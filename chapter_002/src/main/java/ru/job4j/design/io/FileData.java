package ru.job4j.design.io;

import java.util.Objects;

public class FileData {
    String name;
    long size;

    public FileData(String name, long size) {
        this.name = name;
        this.size = size;
    }
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
}
