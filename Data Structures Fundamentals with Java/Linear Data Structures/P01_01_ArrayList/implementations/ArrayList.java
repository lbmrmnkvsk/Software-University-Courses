package implementations;

import interfaces.List;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {

    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[4];
        this.size = 0;
    }

    @Override
    public boolean add(E element) {
        if (this.size == this.elements.length) {
            this.elements = grow();
        }
        this.elements[size++] = element;
        return true;
    }

    @Override
    public boolean add(int index, E element) {
        checkIndex(index);
        insert(index, element);
        return true;
    }

    private void insert(int index, E element) {
        if (this.size == this.elements.length) {
            this.elements = grow();
        }

        for (int i = this.size; i > index; i--) {
            this.elements[i] = this.elements[i - 1];
        }

        this.elements[index] = element;
        this.size++;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) this.elements[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E elementToReturn = (E) this.elements[index];
        this.elements[index] = element;
        return elementToReturn;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E elementToReturn = (E) this.elements[index];
        this.elements[index] = null;
        shiftLeft(index);
        this.size--;
        ensureCapacity();
        return elementToReturn;
    }

    private void shiftLeft(int index) {
        for (int i = index; i <= this.size - 1; i++) {
            this.elements[i] = this.elements[i + 1];
        }
    }

    private void ensureCapacity() {
        if (this.size < this.elements.length / 3) {
            this.elements = shrink();
        }
    }

    private Object[] shrink() {
        return Arrays.copyOf(this.elements, this.elements.length / 2);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int indexOf(E element) {
        if (this.size == 0) {
            return -1;
        }

        for (int i = 0; i <= this.elements.length - 1; i++) {
            if (this.elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return this.index < size();
            }

            @Override
            public E next() {
                return get(index++);
            }
        };
    }

    private Object[] grow() {
        return Arrays.copyOf(this.elements, this.elements.length * 2);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
    }
}
