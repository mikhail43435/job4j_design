package ru.job4j.design.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException(
                    "Root folder is null. Usage java -jar dir.jar ROOT_FOLDER FILE_EXT_MASK");
        }
        if (args.length == 1) {
            throw new IllegalArgumentException(
                    "File extension mask is null. Usage java -jar dir"
                            + ".jar ROOT_FOLDER FILE_EXT_MASK");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format(
                    "Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format(
                    "Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        System.out.println("Printing files in directory with extension '"
                + args[1] + "': " + file.getName());
        for (File subfile : file.listFiles()) {
            if (subfile.getName().endsWith(args[1]) && !subfile.isDirectory()) {
                System.out.println("file name: " + subfile.getName()
                        + "   file size: " + subfile.length());
            }
        }
    }
}