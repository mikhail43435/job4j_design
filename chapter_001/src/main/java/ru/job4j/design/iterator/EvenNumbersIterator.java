package ru.job4j.design.iterator;

import java.util.NoSuchElementException;

public class EvenNumbersIterator {

    private final int[] data;
    private int nextEvenPosition;

    public EvenNumbersIterator(int[] numbers) {
        data = numbers;
        nextEvenPosition = nextEvenPosition(0);
    }

    public boolean hasNext() {
        return nextEvenPosition >= 0;
    }

    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int position = nextEvenPosition;
        nextEvenPosition = nextEvenPosition(nextEvenPosition + 1);
        return data[position];
    }

    private int nextEvenPosition(int start) {

        if (start >= data.length) return -1;

        for (int index = start; index < data.length; index++) {
            if (data[index] % 2 == 0) {
                return index;
            }
        }
        return -1;
    }
}
