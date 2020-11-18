package ru.job4j.design.tree;

import java.util.*;
import java.util.function.Predicate;

class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> node = findBy(parent);
        if (node.isPresent() && findBy(child).isEmpty()) {
            node.get().children.add(new Node<>(child));
            return true;
        }
        return false;
    }

    public boolean isBinary() {
        return searchBy(e -> (e.children.size() > 2)) == null;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Node<E> node = searchBy(e -> e.value.equals(value));
        return node != null ? Optional.of(node) : Optional.empty();
    }

    private Node<E> searchBy(Predicate<Node<E>> filter) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (filter.test(el)) {
                return el;
            }
            data.addAll(el.children);
        }
        return null;
    }
}