package ru.job4j.design.list.linkedarray;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
//import static ru.job4j.design.list.linkedarray;

import org.junit.Test;
import ru.job4j.design.list.arraylist.SimpleArray;
//import org.w3c.dom.Node;
//import ru.job4j.design.list.linkedarray;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedArrayTest {

    @Test
    public void whenAddThenGetLast() {
        LinkedArray<String> array = new LinkedArray<>();
        array.add("0");
        array.add("1");
        String rsl = array.get(1);
        assertThat(rsl, is("1"));
    }

    @Test
    public void whenAddThenGetFirst() {
        LinkedArray<String> array = new LinkedArray<>();
        array.add("0");
        array.add("1");
        String rsl = array.get(0);
        assertThat(rsl, is("0"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        LinkedArray<String> array = new LinkedArray<>();
        array.add("first");
        array.get(1);
    }

    @Test
    public void whenAddThenHasIt() {
        LinkedArray<String> array = new LinkedArray<>();
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenHasIt2() {
        LinkedArray<String> array = new LinkedArray<>();
        array.add("first");
        array.add("second");
        Iterator<String> it = array.iterator();
        assertThat(it.next(), is("first"));
        assertThat(it.next(), is("second"));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        LinkedArray<String> array = new LinkedArray<>();
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        LinkedArray<String> array = new LinkedArray<>();
        array.iterator().next();
    }

}