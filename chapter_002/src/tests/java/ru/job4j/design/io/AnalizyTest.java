package ru.job4j.design.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenStandard() throws IOException {
        String source = "./data/server1.log";
        File target = folder.newFile("unavailable.csv");
        Analizy.unavailable(source, target.getAbsolutePath());
        assertThat(readLogFiles(target.getAbsolutePath()),
                is("10:58:01;10:59:01;%%11:01:02;11:02:02;"));
    }

    @Test
    public void whenEmpty() throws IOException {
        String source = "./data/server2.log";
        File target = folder.newFile("unavailable.csv");
        Analizy.unavailable(source, target.getAbsolutePath());
        assertThat(readLogFiles(target.getAbsolutePath()), is(""));
    }

    @Test
    public void whenLastPeriodIsNotOver() throws IOException {
        String source = "./data/server3.log";
        File target = folder.newFile("unavailable.csv");
        Analizy.unavailable(source, target.getAbsolutePath());
        assertThat(readLogFiles(target.getAbsolutePath()),
                is("10:58:01;10:59:01;%%11:01:02;;"));
    }

    @Test
    public void whenLostOf400And500InARow() throws IOException {
        String source = "./data/server4.log";
        File target = folder.newFile("unavailable.csv");
        Analizy.unavailable(source, target.getAbsolutePath());
        assertThat(readLogFiles(target.getAbsolutePath()),
                is("10:58:01;11:59:01;%%12:01:02;;"));
    }

    private String readLogFiles(String source) {
        String rsl = "";
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            rsl = in.lines().collect(Collectors.joining("%%"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }
}