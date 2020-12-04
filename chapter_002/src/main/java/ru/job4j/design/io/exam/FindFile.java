package ru.job4j.design.io.exam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.design.io.SearchFiles;
import ru.job4j.design.log.UsageLog4j;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FindFile {

    private static final Logger LOG =
            LoggerFactory.getLogger(UsageLog4j.class.getName());

    public FindFile(String[] args) throws IOException {
        main(args);
    }

    public static void main(String[] args) throws IOException {
        FindFileArg param = new FindFileArg(args, LOG);
        if (!param.isValid()) {
            LOG.error("Invalid parameters. "
                    + "See documentation for further information");
            throw new IllegalArgumentException("Invalid parameters. "
                    + "See documentation for further information");
        }
        search(param);
    }

    private static void search(FindFileArg param) throws IOException {
        SearchFiles searcher = new SearchFiles(getPredicate(param.searchMode(), param.mask()));
        Path root = Path.of(param.directory());
        if (!root.toFile().exists()) {
            LOG.error("Указанной в ключе -d папки не существует!");
            throw new NoSuchFileException("Указанной в ключе -d папки не существует!");
        }

        Files.walkFileTree(root, searcher);
        outputResult(searcher.getPaths(), param);
    }

    private static Predicate getPredicate(int searchMode, String mask) {
        Predicate<Path> pred = path -> path.toFile().exists();
        if (searchMode == 1) {
            String regex = mask.replace("?", ".?")
                    .replace("*", ".*?");
            pred = path -> path.toFile().getName().matches(regex);
            LOG.info("Поиск файла по маске."
                    + " Маска: " + mask);
        }
        if (searchMode == 2) {
            pred = path -> path.toFile().getName().equals(mask);
            LOG.info("Поиск файла по полному"
                    + " совпадению имени. Имя файла: " + mask);
        }
        if (searchMode == 3) {
            Pattern p = Pattern.compile(mask);
            pred = path -> p.asPredicate().test(path.toFile().getName());
            LOG.info("Поиск файла по регулярному выражению."
                    + " Регулярное выражение: " + mask);
        }
        return pred;
    }

    private static void outputResult(List<Path> list, FindFileArg param) {
        LOG.info("Вывожу результат поиска файлов...");
        LOG.info("Всего найдено " + list.size() + " файлов");
        if (list.size() > 0) {
            LOG.info("------------------------");
            list.forEach(e -> LOG.info(e.toString()));
            LOG.info("------------------------");
        }
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(new FileOutputStream(param.outputPath())))) {
            Consumer<Path> cons = e -> out.write(e + System.getProperty("line.separator"));
            list.forEach(cons);
            LOG.info("Результаты поиска сохранены в файл: " + param.outputPath());
        } catch (FileNotFoundException e) {
            LOG.info("Невозможно создать файл для сохранения результатов поиска");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOG.info("Работа завершена");
    }
}