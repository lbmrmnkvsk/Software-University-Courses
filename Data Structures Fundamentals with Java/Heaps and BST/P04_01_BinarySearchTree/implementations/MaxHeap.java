package implementations;

import interfaces.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxHeap<E extends Comparable<E>> implements Heap<E> {
    private List<E> elements;

    public MaxHeap() {
        this.elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public void add(E element) {
        this.elements.add(element);
        this.heapifyUp(this.size() - 1);
    }

    private void heapifyUp(int index) {
        int parentIndex = getParentIndex(index);
        while (index > 0 && isLess(index, parentIndex) ) {
            Collections.swap(this.elements, index, parentIndex);
            index = parentIndex;
            parentIndex = getParentIndex(index);
        }
    }

    private boolean isLess(int childIndex, int parentIndex) {
        return this.elements.get(childIndex).compareTo(this.elements.get(parentIndex)) > 0;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    @Override
    public E peek() {
        if (this.elements.isEmpty()) {
            throw new IllegalStateException("MaxHeap is empty");
        }

        return this.elements.get(0);
    }
}
