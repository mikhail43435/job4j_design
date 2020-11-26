package ru.job4j.design.generic.simplearray;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import java.util.NoSuchElementException;
import org.junit.Test;

public class SimpleArrayItTest {

    @Test
    public void whenReadSequence() {
        SimpleArray<Integer> sa = new SimpleArray<>(3);
        sa.add(1);
        sa.add(2);
        sa.add(3);
        SimpleArrayIt it = new SimpleArrayIt(sa);
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
    }

     @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        SimpleArray sa = new SimpleArray(0);
        SimpleArrayIt it = new SimpleArrayIt(sa);
        it.next();
    }

}