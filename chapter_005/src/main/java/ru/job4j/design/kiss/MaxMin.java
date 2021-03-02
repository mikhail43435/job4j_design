package ru.job4j.design.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        BiPredicate<T, T> predicate = (t, u) -> comparator.compare(t, u) < 0;
        return getValue(value, predicate);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        BiPredicate<T, T> predicate = (t, u) -> comparator.compare(t, u) > 0;
        return getValue(value, predicate);
    }

    private <T> T getValue(List<T> list, BiPredicate<T, T> predicate) {
        if (list.isEmpty()) return null;
        T value = list.get(0);
        if (list.size() == 1) return value;
        for (int index = 1; index < list.size(); index++) {
            if (predicate.test(value, list.get(index))) {
                value = list.get(index);
            }
        }
        return value;
    }
}