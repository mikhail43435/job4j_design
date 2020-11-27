package ru.job4j.design.io.bot;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final PrintWriter output;
    private final String path;
    private final String botAnswers;
    private List<DataModel> database;
    private final Date date = new Date();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
        output = createLog("C:\\projects\\job4j_design\\chapter_002\\data\\chatbot\\log.txt");
    }

    public static void main(String[] args) throws FileNotFoundException {
        ConsoleChat cc = new ConsoleChat("", "");
        cc.run();
    }

    public void run() throws FileNotFoundException {
        database =
                loadDatabase("C:\\projects\\job4j_design"
                        +"\\chapter_002\\data\\chatbot\\database.txt");
        String in;
        boolean chatRunning = true;
        boolean chatIsActive = true;
        Scanner scanner = new Scanner(System.in);
        logging("Bot started ", 3);
        output("Добро пожаловать в бот-чат! Введите слово!", 1);
        output("Вам будет выведена строка из книги \"Война и мир\"", 1);
        while (chatRunning) {
            in = scanner.nextLine();
            logging(in, 2);
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
            String answer = getAnswer(in);
            output((answer.isEmpty() ? "К сожалению,"
                    + " подходящей строки не найдено." : answer), 1);
            if (in.equals(OUT)) {
                chatRunning = false;
            }
        }
        output("Чат-бот закончил свою работу! Всего хорошего!", 1);
        logging("Bot terminate ", 3);
        output.close();
    }

    private void output(String line, int messageStatus) {
        System.out.println(line);
        logging(line, messageStatus);
    }

    private void logging(String line, int messageStatus) {
        try {
        String stringPref = switch (messageStatus) {
            case 2 -> "User input " + date.toString();
            case 1 -> "Bot output " + date.toString();
            default -> "System message " + date.toString();
        };

            output.write(stringPref
                        + System.getProperty("line.separator")
                        + line
                        + System.getProperty("line.separator")
                        + System.getProperty("line.separator"));
        } catch (Exception e) {
            System.out.println("error 1");
            e.printStackTrace();
        }
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

    private String getAnswer(String word) {
        List<String> listWithAnswers = database
                .stream()
                .filter(e -> e.keyWords.contains(word))
                .map(e -> e.sentence)
                .collect(Collectors.toList());
        if (listWithAnswers.size() == 0) return "";
        output("Найдено " + listWithAnswers.size() + " строк", 1);
        return listWithAnswers.get((int) (Math.random() * (listWithAnswers.size() - 1)));
    }

    private List<DataModel> loadDatabase(String filename) throws FileNotFoundException {
        List<DataModel> database = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(
                new FileReader(filename, Charset.forName("WINDOWS-1251")))) {
            String line;
            while ((line = in.readLine()) != null) {
                database.add(new DataModel(
                        new HashSet<>(Arrays.asList(line.split(" "))),
                        line));
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Database file not found");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return database;
    }

    private class DataModel {
        HashSet<String> keyWords;
        String sentence;

        public DataModel(HashSet<String> keyWords, String sentence) {
            this.keyWords = keyWords;
            this.sentence = sentence;
        }
    }
}