package ru.job4j.design.io.zip;

import ru.job4j.design.io.SearchFiles;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            for (Path e : sources) {
                zip.putNextEntry(new ZipEntry(e.toString()));
                try (BufferedInputStream out =
                             new BufferedInputStream(new FileInputStream(e.toFile()))) {
                    zip.write(out.readAllBytes());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        /*new Zip().packSingleFile(
                new File("./chapter_005/pom.xml").toPath().toFile(),
                new File("./chapter_005/pom.zip").toPath().toFile()
        );*/
        ArgZip param = new ArgZip(args);
        if (!param.valid()) {
            throw new IllegalArgumentException("Invalid parameters. "
                    + "See documentation for further information");
        }
        SearchFiles searcher = new SearchFiles(path ->
                !path.toFile().getName().endsWith(param.exclude()));
        Path root = Path.of(".");
        //Path root = Path.of(param.directory());
        //Path root = Paths.get(param.directory());

        System.out.println(root.getClass());
        System.out.println(root);

        //Path output = Path.of(param.output());
        Files.walkFileTree(root, searcher);
        //List<Path> list = searcher.getPaths();
        new Zip().packFiles(searcher.getPaths(), Path.of(param.output()));
    }
}
