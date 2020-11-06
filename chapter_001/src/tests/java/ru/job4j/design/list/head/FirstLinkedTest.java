package ru.job4j.design.list.head;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
//import static org.junit.Assert.assertThat;

public class FirstLinkedTest {

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirst() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.deleteFirst();
        linked.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.deleteFirst();
    }

    @Test
    public void whenMultiDelete() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.deleteFirst();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
        assertThat(linked.isEmpty(), is(false));
    }

    @Test
    public void whenFullAndThenEmpty() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        assertThat(linked.isEmpty(), is(false));
        linked.deleteFirst();
        assertThat(linked.isEmpty(), is(true));
    }
}