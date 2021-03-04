package ru.job4j.design.tdd.template;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class GeneratorBasicTest {

    @Test
    public void testThenBasicTest() {
        GeneratorBasic generator = new GeneratorBasic();
        String stringIn = "I am a ${name}, Who are ${subject}";
        String stringOut = "I am Petr, I am 31 years old";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr");
        map.put("age", "31");
        assertThat(generator.produce(stringIn, map), is(stringOut));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThenKeyNotFound() {
        GeneratorBasic generator = new GeneratorBasic();
        String stringIn = "I am ${name}, I am ${subject} years old";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr");
        generator.produce(stringIn, map);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThenKeyNotExist() {
        GeneratorBasic generator = new GeneratorBasic();
        String stringIn = "I am ${name}";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr");
        map.put("age", "31");
        generator.produce(stringIn, map);
    }
}