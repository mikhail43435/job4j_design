package ru.job4j.design.set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

    public class SimpleSetTest {

        @Test
        public void whenAddThenGetInteger() {
            SimpleSet<Integer> array = new SimpleSet<>();
            array.add(1);
            array.add(1);
            Integer rsl = array.get(0);
            assertThat(rsl, is(1));
            assertThat(array.getSize(), is(1));
        }

        @Test
        public void whenAddThenGetString() {
            SimpleSet<String> array = new SimpleSet<>();
            array.add("one");
            array.add("one");
            String rsl = array.get(0);
            assertThat(rsl, is("one"));
            assertThat(array.getSize(), is(1));
        }

        @Test
        public void whenAddThenIt() {
            SimpleSet<String> array = new SimpleSet<>();
            array.add("first");
            String rsl = array.iterator().next();
            assertThat(rsl, is("first"));
        }
    }