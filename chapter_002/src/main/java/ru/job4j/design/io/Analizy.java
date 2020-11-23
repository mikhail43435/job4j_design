package ru.job4j.design.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class Analizy {
    public static void unavailable(String source, String target) {
        List<String> data = new ArrayList<>();
        BiFunction<String, Integer, String> putter = (string, code) ->
                code == 0 ? string.split("=")[0] : string.split("=")[1];
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            AtomicBoolean flagCriticalPeriodLasts = new AtomicBoolean(false);
            Predicate<String> statusCriticallyChanged =
                    e -> (e.startsWith("400") || e.startsWith("500"));
            in.lines().
                    filter(e -> (!e.isEmpty())).
                    forEach(e -> {
                        if (flagCriticalPeriodLasts.get()) {
                            if (!statusCriticallyChanged.test(e)) {
                                data.add(e.substring(4));
                                flagCriticalPeriodLasts.set(false);
                            }
                        } else if (statusCriticallyChanged.test(e)) {
                            data.add(e.substring(4));
                            flagCriticalPeriodLasts.set(true);
                        }
                    }
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            Iterator<String> it = data.iterator();
            while (it.hasNext()) {
                out.println(it.next() + ";" + (it.hasNext() ? it.next() : "") + ";");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
/*        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}