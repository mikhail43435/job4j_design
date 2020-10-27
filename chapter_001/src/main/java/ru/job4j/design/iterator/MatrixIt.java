package ru.job4j.design.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while ((row < data.length) && data[row].length == 0) {
            row++;
            column = 0;
        }
        return row < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
/*        if (data[row].length == 0) {
            do {
                row++;
            } while (data[row].length == 0);
        }
        if (column == data[row].length - 1) {
            int rsl = data[row][column];
            column = 0;
            row++;
            return rsl;
        } else {
            return data[row][column++];
        }*/
    }
}