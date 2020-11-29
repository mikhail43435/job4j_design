package ru.job4j.design.io.bot;

import ru.job4j.design.io.ArgsName;
import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class ConsoleChat {
    private static final String OUT = "завершить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final PrintWriter output;
    private final String logPath;
    private final String databasePath;
    private final HashMap<String, ArrayList<Integer>> database = new HashMap<>();
    private final List<String> stringsDatabase = new ArrayList<>();
    private final List<String> logList = new ArrayList<>();
    private final Date date = new Date();

    public ConsoleChat(String logPath, String databasePath) {
        this.logPath = logPath;
        this.databasePath = databasePath;
        output = createLog(logPath);
        output("Инициализация", 3);
        output("Путь лог-файла: " + logPath, 3);
        output("Путь файла базы данных: " + databasePath, 3);
    }

    public static void main(String[] args) throws IllegalArgumentException, FileNotFoundException {
        ArgsName arguments = ArgsName.of(args);
        if (isNull(arguments.get("log")) || isNull(arguments.get("data"))) {
            System.out.println();
            throw new IllegalArgumentException("Missing start parameter! "
                    +  "Start with -LOG=LOGFILE and -DATA=DATAFILE keys!");
        }
        ConsoleChat cc = new ConsoleChat(arguments.get("log"), arguments.get("data"));
        cc.run();
    }

    public void run() throws FileNotFoundException {
        output("Загрузка чат-бота...", 1);
        loadDatabase(databasePath);
        output("Загрузка базы данных, путь: " + databasePath, 3);
        String in;
        boolean chatRunning = true;
        boolean chatIsActive = true;
        Scanner scanner = new Scanner(System.in);
        output("Bot started ", 3);
        output("Добро пожаловать в бот-чат! Введите слово!", 1);
        output("Вам будет выведена одна из строк "
                + "из книги \"Война и мир\" содержащая данное слово", 1);
        output("Используйте следущие команды для управлениче чат-ботом:", 1);
        output("Остановить чат чат:" + STOP, 1);
        output("Продолжить чат:" + CONTINUE, 1);
        output("Завершить чат:" + OUT, 1);
        while (chatRunning) {
            in = scanner.nextLine();
            output("Введено слово: " + in, 2);
            if (in.equals(STOP) && chatIsActive) {
                chatIsActive = false;
                output("Чат-бот переведен в пассивный режим. "
                        + "Для продолжения работы введите слово \"продолжить\"!", 1);
            }
            if (in.equals(CONTINUE) && !chatIsActive) {
                chatIsActive = true;
                output("Чат-бот переведен активный режим!", 1);
                continue;
            }
            if (!chatIsActive) continue;
            if (in.equals(OUT)) {
                chatRunning = false;
                continue;
            }
            Instant start = Instant.now();
            String answer = getAnswer(in);
            Instant finish = Instant.now();
            output("На выполнение поиска было потрачено "
                    + (Duration.between(start, finish).toNanos())
                    + " наносекунд", 1);
            if (!answer.isEmpty()) {
                output("Найденная строка: " + answer, 1);
            }
        }
        output("Чат-бот закончил свою работу! Всего хорошего!", 1);
        output("Bot terminate ", 3);
        saveLogData();
    }

    private void output(String line, int messageStatus) {
        if (messageStatus == 1 || messageStatus == 2) {
            System.out.println(line);
        }
        logging(line, messageStatus);
    }

    private void logging(String line, int messageStatus) {
        String stringPref = switch (messageStatus) {
            case 2 -> "User input ";
            case 1 -> "Bot output ";
            default -> "System message ";
        };
        logList.add(stringPref
                + new SimpleDateFormat("yyyyMMdd#HHmmss")
                .format(Calendar.getInstance().getTime()));
        logList.add(System.getProperty("line.separator"));
        logList.add(line);
        logList.add(System.getProperty("line.separator"));
        logList.add(System.getProperty("line.separator"));
    }

    private PrintWriter createLog(String filename) {
          try {
              PrintWriter out = new PrintWriter(
                      new BufferedOutputStream(new FileOutputStream(filename)));
              return out;
          } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void saveLogData() {
        try {
            logList.forEach(output::write);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getAnswer(String word) {
        List<String> listWithAnswers = new ArrayList<>();
        ArrayList<Integer> data = database.get(word);
        if (nonNull(data) && data.size() != 0) {
            data.forEach(e -> listWithAnswers.add(stringsDatabase.get(e)));
            output("Найдено " + listWithAnswers.size() + " строк", 1);
            return listWithAnswers.get((int) (Math.random() * (listWithAnswers.size())));
        } else {
            output("Подходящих строк не найдено", 1);
        }
        return "";
    }

    private void loadDatabase(String filename) throws FileNotFoundException {
        output("Начало загрузки базы данных", 3);
        HashSet<String> tempStringHashSet = new HashSet<>();
        try (BufferedReader in = new BufferedReader(
                new FileReader(filename, Charset.forName("WINDOWS-1251")))) {
            String line;
            while (nonNull(line = in.readLine())) {
                    stringsDatabase.add(line.trim());
                for (String string : line.split(" ")) {
                    tempStringHashSet.add(string.trim());
                }
            }

            for (String el : tempStringHashSet) {
                ArrayList<Integer> tempArray = new ArrayList<>();
                for (int index = 0; index < stringsDatabase.size(); index++) {
                    if (stringsDatabase.get(index).contains(el)) {
                        tempArray.add(index);
                    }
                }
                database.put(el, tempArray);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Database file not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
        output("Окончание загрузки базы данных", 3);
    }
}