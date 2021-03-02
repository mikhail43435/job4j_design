package ru.job4j.design.kiss;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import org.junit.Assert;
import java.util.Comparator;
import java.util.List;

public class MaxMinTest {

    private final Comparator<Integer> integerComparator =
            (o1, o2) -> (o1 > o2 ? 1 : (o1 == o2 ? 0 : -1));

    @Test
    public void testThenMax() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(4, 2, 1, 2, 3, 6, 7, 5, 10, 6);
        assertThat(maxMin.max(list, integerComparator), is(10));
    }

    @Test
    public void testThenMin() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(4, 2, 1, 2, 3, 6, 7, 5, 10, 6);
        assertThat(maxMin.min(list, integerComparator), is(1));
    }

    @Test
    public void testThenOneElement() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(4);
        assertThat(maxMin.max(list, integerComparator), is(4));
    }

    @Test
    public void testThenTwoElements() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(4, 5);
        assertThat(maxMin.max(list, integerComparator), is(5));
    }

    @Test
    public void testThenEmpty() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of();
        Assert.assertNull(maxMin.max(list, integerComparator));
    }
}