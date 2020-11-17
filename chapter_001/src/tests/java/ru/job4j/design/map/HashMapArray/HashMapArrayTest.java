package ru.job4j.design.map.HashMapArray;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.hamcrest.core.IsNull;
import org.junit.Test;

import java.util.Iterator;

public class HashMapArrayTest {

    @Test
    public void whenAddThenGetStrings() {
        HashMapArray<String, String> array = new HashMapArray<>();
        array.insert("one", "first value");
        array.insert("one", "two");
        String rsl = array.get("one");
        assertThat(rsl, is("first value"));
    }

    @Test
    public void whenAddThenHasIt() {
        HashMapArray<String, String> array = new HashMapArray<>();
        array.insert("one", "first value");
        assertThat(array.next(), is("first value"));
        assertThat(array.hasNext(), is(false));

    }

    @Test
    public void whenNullThenHasIt() {
        HashMapArray<String, String> array = new HashMapArray<>();
        assertThat(array.hasNext(), is(false));
    }

    @Test
    public void whenAddAndExist() {
        HashMapArray<String, String> array = new HashMapArray<>();
        array.insert("one", "first value");
        assertThat(array.insert("one", "first value"), is(false));
    }

    @Test
    public void whenAddThenGetInteger() {
        HashMapArray<Integer, Integer> array = new HashMapArray<>();
        array.insert(1, 1);
        array.insert(1, 2);
        array.insert(2, 2);
        Integer rsl = array.get(1);
        assertThat(rsl, is(1));
        //assertThat(array.getSize(), is(2));
    }

    @Test
    public void whenAddReturnTrue() {
        HashMapArray<String, String> array = new HashMapArray<>();
        array.insert("one", "first value");
        assertThat(array.insert("two", "first value"), is(true));
    }

    @Test
    public void whenAddReturnFalse() {
        HashMapArray<String, String> array = new HashMapArray<>();
        array.insert("one", "first value");
        assertThat(array.insert("one", "first value"), is(false));
    }

    @Test
    public void whenAddThenDeleteSuccessful() {
        HashMapArray<String, String> array = new HashMapArray<>();
        array.insert("one", "first value");
        array.insert("two", "two");
        assertThat(array.delete("one"), is(true));
        assertThat(array.get("one"), is(IsNull.nullValue()));
    }

    @Test
    public void whenAddThenDeleteNotSuccessful() {
        HashMapArray<String, String> array = new HashMapArray<>();
        array.insert("one", "first value");
        array.insert("two", "two");
        assertThat(array.delete("three"), is(false));
    }

    @Test
    public void whenGrow() {
        HashMapArray<Integer, Integer> array = new HashMapArray<>();
        array.insert(1, 1);
        array.insert(2, 2);
        array.insert(3, 2);
        array.insert(4, 2);
        array.insert(5, 2);
        array.insert(6, 2);
        array.insert(7, 2);
        array.insert(8, 2);
        array.insert(9, 2);
        array.insert(10, 2);
        array.insert(11, 2);
        array.insert(12, 12);
        array.insert(13, 12);
        assertThat(array.get(12), is(12));
        //assertThat(array.getSize(), is(13));
        //assertThat(array.getLength(), is(32));
    }
}