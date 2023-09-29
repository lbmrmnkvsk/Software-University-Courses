package implementations;

import interfaces.AbstractQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {
    private List<E> elements;

    public PriorityQueue() {
        this.elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    private void ensureNonEmpty() {
        if (this.size() == 0) {
            throw new IllegalStateException("The priority queue is empty");
        }
    }

    @Override
    public void add(E element) {
        this.elements.add(element);
        this.heapifyUp(this.size() - 1);
    }

    private void heapifyUp(int index) {
        while (index > 0 && isLess(getParentIndex(index), index)) {
            Collections.swap(this.elements, index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    @Override
    public E peek() {
        ensureNonEmpty();
        return this.elements.get(0);
    }

    @Override
    public E poll() {
        ensureNonEmpty();
        E element = this.elements.get(0);
        Collections.swap(this.elements, 0, this.size() - 1);
        this.elements.remove(this.size() - 1);
        this.heapifyDown(0);
        return element;
    }

    private void heapifyDown(int index) {
        while (getLeftChildIndex(index) < this.size()) {
            int child = -1;
            if (isLess(index, getLeftChildIndex(index))) {
                child = getLeftChildIndex(index);
            }

            if (getRightChildIndex(index) < this.size() && isLess(getLeftChildIndex(index), getRightChildIndex(index))) {
                child = getRightChildIndex(index);
            }

            if (child == -1) {
                break;
            }

            Collections.swap(this.elements, index, child);
            index = child;
        }
    }

    private boolean isLess(int index1, int index2) {
        return this.elements.get(index1).compareTo(this.elements.get(index2)) < 0;
    }

    private int getLeftChildIndex(int index) {
        return (2 * index) + 1;
    }

    private int getRightChildIndex(int index) {
        return (2 * index) + 2;
    }

    private E getLeftChild(int index) {
        return this.elements.get(getLeftChildIndex(index));
    }

    private E getRightChild(int index) {
        return this.elements.get(getRightChildIndex(index));
    }
}
