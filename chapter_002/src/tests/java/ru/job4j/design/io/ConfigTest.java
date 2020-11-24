package ru.job4j.design.io;

import org.junit.Test;
import java.lang.IllegalArgumentException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment1.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name"),
                is("Petr Arsentev")
        );
    }

    @Test
    public void whenStingWithWrongChar() {
        String path = "./data/pair_without_comment2.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name"),
                is("Petr Arsentev")
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenIsIllegalArgumentException() {
        String path = "./data/pair_without_comment4.properties";
        Config config = new Config(path);
        config.load();
    }
}