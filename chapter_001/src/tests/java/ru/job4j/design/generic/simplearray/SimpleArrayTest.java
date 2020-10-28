package ru.job4j.design.generic.simplearray;

import static org.hamcrest.Matchers.is;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleArrayTest {

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddOneExtra() {
        SimpleArray<String> array = new SimpleArray<>(2);
        array.add("1");
        array.add("2");
        array.add("3");
    }

    @Test
    public void whenGetElementFirst() {
        SimpleArray<Integer> array = new SimpleArray<>(2);
        array.add(1);
        array.add(2);
        assertThat(array.get(0), is(1));
    }

    @Test
    public void whenGetElementLast() {
        SimpleArray<Integer> array = new SimpleArray<>(2);
        array.add(1);
        array.add(2);
        assertThat(array.get(1), is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetElementException() {
        SimpleArray<Integer> array = new SimpleArray<>(2);
        array.add(1);
        array.add(2);
        array.get(3);
    }

    @Test
    public void whenSetElementFirst() {
        SimpleArray<Integer> array = new SimpleArray<>(2);
        array.add(1);
        array.add(2);
        array.set(0, 5);
        assertThat(array.get(0), is(5));
    }

    @Test
    public void whenSetElementLast() {
        SimpleArray<Integer> array = new SimpleArray<>(2);
        array.add(1);
        array.add(2);
        array.set(1, 5);
        assertThat(array.get(1), is(5));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetElementException() {
        SimpleArray<Integer> array = new SimpleArray<>(2);
        array.add(1);
        array.add(2);
        array.set(2, 5);
    }

    @Test
    public void whenRemoveElementMiddle() {
        SimpleArray<Integer> array = new SimpleArray<>(3);
        array.add(1);
        array.add(2);
        array.add(3);
        array.remove(1);
        assertThat(array.get(1), is(3));
    }

    @Test
    public void whenRemoveElementLast() {
        SimpleArray<Integer> array = new SimpleArray<>(3);
        array.add(1);
        array.add(2);
        array.add(3);
        array.remove(2);
        array.add(11);
        assertThat(array.get(2), is(11));
    }

    @Test
    public void whenRemoveElementFirst() {
        SimpleArray<Integer> array = new SimpleArray<>(3);
        array.add(1);
        array.add(2);
        array.add(3);
        array.remove(0);
        assertThat(array.get(0), is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveElementException() {
        SimpleArray<Integer> array = new SimpleArray<>(2);
        array.add(1);
        array.add(2);
        array.remove(3);
    }
}