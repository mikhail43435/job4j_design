package ru.job4j.design.list.listiterator;

import org.hamcrest.core.Is;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.MatcherAssert.assertThat;
//import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void whenAddBeforeToBegin() {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 3));
        ListUtils.addBefore(input, 0, 1);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterInMiddle() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 4));
        ListUtils.addAfter(input, 1, 3);
        assertThat(input, is(Arrays.asList(1, 2, 3, 4)));
    }

    @Test
    public void whenAddAfterToEnd() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.addAfter(input, 2, 4);
        assertThat(input, is(Arrays.asList(1, 2, 3, 4)));
    }

    @Test
    public void whenAddAfterToBegin() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 0, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 2, 2);
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> inputList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> removeList = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.removeAll(inputList, removeList);
        assertThat(inputList, is(Arrays.asList(4, 5)));
    }

    @Test
    public void whenRemoveAllNothingToRemove() {
        List<Integer> inputList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> removeList = new ArrayList<>(Arrays.asList(7, 8));
        ListUtils.removeAll(inputList, removeList);
        assertThat(inputList, is(Arrays.asList(1, 2, 3, 4, 5)));
    }

    @Test
    public void whenRemoveAllAllToRemove() {
        List<Integer> inputList = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> removeList = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.removeAll(inputList, removeList);
        assertThat(inputList, is(Arrays.asList()));
    }

    @Test
    public void whenRemoveAllRemoveListIsEmpty() {
        List<Integer> inputList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> removeList = new ArrayList<>(Arrays.asList());
        ListUtils.removeAll(inputList, removeList);
        assertThat(inputList, is(Arrays.asList(1, 2, 3, 4, 5)));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> inputList = new ArrayList<>(Arrays.asList(1, 2, 3));
        Predicate<Integer> filter = t -> t == 1;
        ListUtils.removeIf(inputList, filter);
        assertThat(inputList, is(Arrays.asList(2, 3)));
    }

    @Test
    public void whenRemoveIfNoSuchElementToRemove() {
        List<Integer> inputList = new ArrayList<>(Arrays.asList(2, 3));
        Predicate<Integer> filter = t -> t == 1;
        ListUtils.removeIf(inputList, filter);
        assertThat(inputList, is(Arrays.asList(2, 3)));
    }

    @Test
    public void replaceIf() {
        List<Integer> inputList = new ArrayList<>(Arrays.asList(1, 2, 3));
        Predicate<Integer> filter = t -> t == 3;
        ListUtils.replaceIf(inputList, filter, 1);
        assertThat(inputList, is(Arrays.asList(1, 2, 1)));
    }

    @Test
    public void replaceIfNoSuchElementToRemove() {
        List<Integer> inputList = new ArrayList<>(Arrays.asList(2, 3));
        Predicate<Integer> filter = t -> t == 1;
        ListUtils.replaceIf(inputList, filter, 1);
        assertThat(inputList, is(Arrays.asList(2, 3)));
    }

    @Test
    public void replaceIfStringValue() {
        List<String> inputList = new ArrayList<>(Arrays.asList("one", "two", "three"));
        Predicate<String> filter = t -> t.equals("one");
        ListUtils.replaceIf(inputList, filter, "zero");
        assertThat(inputList, is(Arrays.asList("zero", "two", "three")));
    }
}