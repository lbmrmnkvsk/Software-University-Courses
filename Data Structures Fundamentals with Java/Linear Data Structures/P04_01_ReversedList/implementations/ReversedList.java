package implementations;

import java.util.Arrays;
import java.util.Iterator;

public class ReversedList<E> {
    private final int DEFAULT_CAPACITY = 2;
    private int size;
    private Object[] elements;

    public ReversedList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public int capacity() {
        return this.elements.length;
    }

    public void add(E element) {
        if (this.size == this.elements.length) {
            this.elements = Arrays.copyOf(this.elements, this.elements.length * 2);
        }
        this.elements[this.size++] = element;
    }

    public E get(int index) {
        return (E) this.elements[this.size - 1 - index];
    }

    public void removeAt(int index) {
        int realIndex = this.size - 1 - index;
        for (int i = realIndex; i < this.size - 1; i++) {
            this.elements[i] = this.elements[i + 1];
        }

        this.elements[this.size - 1] = null;
        this.size--;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = size - 1;
            @Override
            public boolean hasNext() {
                return this.index > 0;
            }

            @Override
            public E next() {
                return (E) elements[index--];
            }
        };
    }
}
