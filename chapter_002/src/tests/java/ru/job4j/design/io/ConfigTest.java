package ru.job4j.design.io;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

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
    public void whenStingWithWringChar() {
        String path = "./data/pair_without_comment2.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name"),
                is(nullValue())
        );
    }

    @Test
    public void whenIsEmpty() {
        String path = "./data/pair_without_comment3.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is(nullValue()));
    }
}