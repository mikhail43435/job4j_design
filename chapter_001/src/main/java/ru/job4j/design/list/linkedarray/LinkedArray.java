package ru.job4j.design.list.linkedarray;

import ru.job4j.design.list.arraylist.SimpleArrayIt;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.util.Objects.checkIndex;

public class LinkedArray<T> implements Iterable<T> {

    private int size;
    //List<Node<T>> data;
    private Node<T> first;
    private Node<T> last;
    private int modCount;

    public LinkedArray() {
        //data = new LinkedList<>();
    }

    protected int getSize() {
        return size;
    }

    protected int getModCount() {
        return modCount;
    }

    public T get(int index) {
        checkIndex(index, size);
        Node<T> tempNode = first;

        for (int i = 0; i < size; i++) {
          if (i == index) return tempNode.item;
            tempNode = tempNode.next;
        }
        return null;
    }

    public void add(T model) {
        if (first == null) {
            final Node<T> newNode = new Node(null, model, null);
            first = newNode;
            last = newNode;
            //data.add(newNode);
        } else {
            final Node<T> newNode = new Node(last, model, null);
            last.next = newNode;
            last = newNode;
            //data.add(newNode);
        }
        size++;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedArrayIt(this);
    }

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T element, Node<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public class LinkedArrayIt<T> implements Iterator<T> {

        private final LinkedArray<T> data;
        private Node<T> pointer;
        private final int expectedModCount;
        private int counter;


        public LinkedArrayIt(LinkedArray<T> data) {
            this.expectedModCount = data.getModCount();
            this.data = data;
            this.pointer = data.first;
        }

        @Override
        public boolean hasNext() {
            if (!(expectedModCount == data.getModCount())) {
                throw new ConcurrentModificationException();
            }
            return (counter < data.size);
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T itemToReturn = pointer.item;
            pointer = pointer.next;
            counter++;
            return itemToReturn;
        }
    }


}
