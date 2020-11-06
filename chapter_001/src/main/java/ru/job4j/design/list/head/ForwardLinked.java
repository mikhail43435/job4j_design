package ru.job4j.design.list.head;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private boolean isEmpty = true;

    public void add(T value) {
        isEmpty = false;

        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T valueToReturn = head.value;
        head = head.next;
        isEmpty = (head == null);
        return valueToReturn;
    }

    public T deleteLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        if (head.next == null) {
            T valueToReturn = head.value;
            head = null;
            isEmpty = true;
            return valueToReturn;
        }
        Node<T> tail = head;
        Node<T> prevTail = null;
        while (tail.next != null) {
            prevTail = tail;
            tail = tail.next;
        }
        prevTail.next = null;
        return tail.value;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}