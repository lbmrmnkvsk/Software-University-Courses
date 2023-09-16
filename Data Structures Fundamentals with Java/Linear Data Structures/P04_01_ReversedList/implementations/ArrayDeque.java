package implementations;

import interfaces.Deque;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayDeque<E> implements Deque<E> {

    private final int DEFAULT_CAPACITY = 7;
    private int head;
    private int tail;
    private int size;
    private Object[] elements;

    public ArrayDeque() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.head = this.elements.length / 2;
        this.tail = this.head;
    }

    @Override
    public void add(E element) {
        this.addLast(element);
    }

    @Override
    public void offer(E element) {
        addFirst(element);
    }

    @Override
    public void addFirst(E element) {
        if (this.isEmpty()) {
            this.elements[this.tail] = element;
        } else {
            if (this.head == 0) {
                this.elements = grow();
            }
            this.elements[--this.head] = element;
        }
        this.size++;
    }

    @Override
    public void addLast(E element) {
        if (this.isEmpty()) {
            this.elements[this.tail] = element;
        } else {
            if (this.tail == this.elements.length - 1) {
                this.elements = grow();
            }

            this.elements[++this.tail] = element;
        }
        this.size++;
    }

    private Object[] grow() {
        int newCapacity = (this.elements.length * 2) + 1;
        Object[] newElements = new Object[newCapacity];
        int middle = newCapacity / 2;
        int begin = middle - (this.size / 2);

        for (int i = this.head; i <= this.tail; i++) {
            newElements[begin++] = this.elements[i];
        }

        this.head = begin;
        this.tail = this.head + this.size - 1;
        return newElements;
    }

    @Override
    public void push(E element) {
        addLast(element);
    }

    @Override
    public void insert(int index, E element) {
        int realIndex = this.head + index;
        this.checkIndex(realIndex);

        int middle = (this.head + this.tail) / 2;
        if (realIndex < middle) {
            insertAndShiftLeft(realIndex, element);
        } else {
            insertAndShiftRight(realIndex, element);
        }
    }

    private void insertAndShiftRight(int realIndex, E element) {
        E tailElement = (E) this.elements[this.tail];
        for (int i = this.tail; i > realIndex; i--) {
            this.elements[i] = this.elements[i - 1];
        }
        this.elements[realIndex] = element;
        this.addLast(tailElement);
    }

    private void insertAndShiftLeft(int realIndex, E element) {
        E headElement = (E) this.elements[this.head];
        for (int i = this.head; i < realIndex; i++) {
            this.elements[i] = this.elements[i + 1];
        }
        this.elements[realIndex] = element;
        this.addFirst(headElement);
    }

    @Override
    public void set(int index, E element) {
        int realIndex = this.head + index;
        this.checkIndex(realIndex);
        this.elements[realIndex] = element;
    }

    @Override
    public E peek() {
        if (this.isEmpty()) {
            return null;
        } else {
            return this.getAt(this.head);
        }
    }

    private E getAt(int index) {
        return (E) this.elements[index];
    }

    @Override
    public E poll() {
        return this.removeFirst();
    }

    @Override
    public E pop() {
        return this.removeLast();
    }

    @Override
    public E get(int index) {
        int realIndex = this.head + index;
        checkIndex(realIndex);
        return (E) this.elements[realIndex];
    }

    private void checkIndex(int realIndex) {
        if (realIndex < this.head || realIndex > this.tail) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }

    @Override
    public E get(Object object) {
        if (this.isEmpty()) {
            return null;
        }

        for (int i = this.head; i <= this.tail; i++) {
            if (this.elements[i].equals(object)) {
                return (E) this.elements[i];
            }
        }
        return null;
    }

    @Override
    public E remove(int index) {
        if (this.isEmpty()) {

        }
        int realIndex = this.head + index;
        this.checkIndex(realIndex);
        E elementToReturn = (E) this.elements[realIndex];
        for (int i = realIndex; i < this.tail; i++) {
            this.elements[i] = this.elements[i + 1];
        }
        return this.removeLast();
    }

    @Override
    public E remove(Object object) {
        if (this.isEmpty()) {
            return null;
        }

        int indexToRemove = -1;
        for (int i = this.head; i <= this.tail; i++) {
            if (this.elements[i].equals(object)) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove == -1) {
            return null;
        } else {
            return this.remove(indexToRemove);
        }
    }

    @Override
    public E removeFirst() {
        if (this.isEmpty()) {
            return null;
        } else {
            E elementToReturn = (E) this.elements[this.head];
            this.elements[this.head++] = null;
            this.size--;
            return elementToReturn;
        }
    }

    @Override
    public E removeLast() {
        if (this.isEmpty()) {
            return null;
        } else {
            E element = (E) this.elements[this.tail];
            this.elements[this.tail--] = null;
            this.size--;
            return element;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.elements.length;
    }

    @Override
    public void trimToSize() {
        this.elements = Arrays.copyOf(this.elements, this.size);
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = head;
            @Override
            public boolean hasNext() {
                return this.index < tail;
            }

            @Override
            public E next() {
                return (E) elements[index++];
            }
        };
    }
}
