package implementations;

import interfaces.LinkedList;

import java.util.Iterator;

public class SinglyLinkedList<E> implements LinkedList<E> {
    private Node<E> head;
    private int size;

    private static class Node<E> {
        private E value;
        private Node<E> next;

        Node(E element) {
            this.value = element;
        }
    }

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void addFirst(E element) {
        Node<E> nextElement = new Node<>(element);
        nextElement.next = this.head;
        this.head = nextElement;
        this.size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> nodeToAdd = new Node<>(element);
        if (this.isEmpty()) {
            this.head = nodeToAdd;
            this.size++;
        } else {
            Node<E> current = this.head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = nodeToAdd;
            size++;
        }
    }

    @Override
    public E removeFirst() {
        ensureNonEmpty();
        Node<E> nodeToRemove = this.head;
        this.head = this.head.next;
        this.size--;
        return nodeToRemove.value;
    }

    @Override
    public E removeLast() {
        ensureNonEmpty();

        if (this.size == 1) {
            E value = this.head.value;
            this.head = null;
            this.size--;
            return value;
        } else {
            Node<E> preLast = this.head;
            Node<E> last = this.head.next;

            while (last.next != null) {
                preLast = last;
                last = last.next;
            }

            preLast.next = null;
            this.size--;
            return last.value;
        }
    }

    @Override
    public E getFirst() {
        ensureNonEmpty();
        return this.head.value;
    }

    @Override
    public E getLast() {
        ensureNonEmpty();
        Node<E> current = this.head;
        while (current.next != null) {
            current = current.next;
        }
        return current.value;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return this.current != null;
            }

            @Override
            public E next() {
                E value = this.current.value;
                this.current = this.current.next;
                return value;
            }
        };
    }

    private void ensureNonEmpty() {
        if (this.isEmpty()) {
            throw new IllegalStateException("The list is empty");
        }
    }
}
