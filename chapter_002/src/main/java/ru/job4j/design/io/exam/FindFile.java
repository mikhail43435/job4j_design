package ru.job4j.design.io.exam;

import ru.job4j.design.io.SearchFiles;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FindFile {

    public FindFile(String[] args) throws IOException {
        main(args);
    }

    public static void main(String[] args) throws IOException {
        FindFileArg param = new FindFileArg(args);
        if (!param.isValid()) {
            throw new IllegalArgumentException("Invalid parameters. "
                    + "See documentation for further information");
        }
        Predicate<Path> pred = path -> path.toFile().exists();
        if (param.searchMode() == 1) {
            String regex = param.mask().replace("?", ".?")
                    .replace("*", ".*?");
            pred = path -> path.toFile().getName().matches(regex);
            System.out.println("Поиск файла по маске."
                    + " Маска: " + param.mask());
        }
        if (param.searchMode() == 2) {
            pred = path -> path.toFile().getName().equals(param.mask());
            System.out.println("Поиск файла по полному"
                    + " совпадению имени. Имя файла: " + param.mask());
        }
        if (param.searchMode() == 3) {
            Pattern p = Pattern.compile(param.mask());
            pred = path -> p.asPredicate().test(path.toFile().getName());
            System.out.println("Поиск файла по регулярному выражению."
                    + " Регулярное выражение: " + param.mask());
        }
        SearchFiles searcher = new SearchFiles(pred);
        Path root = Path.of(param.directory());
        if (!root.toFile().exists()) {
            throw new NoSuchFileException("Указанной в ключе -d папки не существует!");
        }

        Files.walkFileTree(root, searcher);
        outputResult(searcher.getPaths(), param);
    }

    private static void outputResult(List<Path> list, FindFileArg param) {
        System.out.println("Вывожу результат поиска файлов...");
        System.out.println("Всего найдено " + list.size() + " файлов");
        if (list.size() > 0) {
            System.out.println("------------------------");
            list.forEach(System.out::println);
            System.out.println("------------------------");
        }
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(new FileOutputStream(param.outputPath())))) {
            Consumer<Path> cons = e -> out.write(e + System.getProperty("line.separator"));
            list.forEach(cons);
            System.out.println("Результаты поиска сохранены в файл: " + param.outputPath());
        } catch (FileNotFoundException e) {
            System.out.println("Невозможно создать файл для сохранения результатов поиска");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Работа завершена");
    }
}