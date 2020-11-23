package ru.job4j.design.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AnalizyTest {

    @Test
    public void whenStandard() {
        String source = "./data/server1.log";
        String target = "./data/unavailable.csv";
        Analizy.unavailable(source, target);
        assertThat(readLogFiles(target), is("10:58:01;10:59:01;%%11:01:02;11:02:02;"));
    }

    @Test
    public void whenEmpty() {
        String source = "./data/server2.log";
        String target = "./data/unavailable.csv";
        Analizy.unavailable(source, target);
        assertThat(readLogFiles(target), is(""));
    }

    @Test
    public void whenLastPeriodIsNotOver() {
        String source = "./data/server3.log";
        String target = "./data/unavailable.csv";
        Analizy.unavailable(source, target);
        assertThat(readLogFiles(target), is("10:58:01;10:59:01;%%11:01:02;;"));
    }

    @Test
    public void whenLostOf400And500InARow() {
        String source = "./data/server4.log";
        String target = "./data/unavailable.csv";
        Analizy.unavailable(source, target);
        assertThat(readLogFiles(target), is("10:58:01;11:59:01;%%12:01:02;;"));
    }

    private String readLogFiles(String sourse) {
        String rsl = "";
        try (BufferedReader in = new BufferedReader(new FileReader(sourse))) {
            rsl = in.lines().collect(Collectors.joining("%%"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }
}