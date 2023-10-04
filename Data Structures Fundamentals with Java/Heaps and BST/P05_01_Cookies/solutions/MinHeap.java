package solutions;

import interfaces.Decrease;
import interfaces.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinHeap<E extends Comparable<E> & Decrease<E>> implements Heap<E> {
    private List<E> data;

    public MinHeap() {
        this.data = new ArrayList<>();
    }
    @Override
    public int size() {
        return this.data.size();
    }

    @Override
    public void add(E element) {
        if (this.size() == 0) {
            this.data.add(element);
        } else {
            this.data.add(element);
            heapifyUp(this.size() - 1);
        }
    }

    private void heapifyUp(int index) {
        int parentIndex = getParentIndex(index);

        while (hasParent(index) && isSmaller(index, parentIndex)) {
            Collections.swap(this.data, index, parentIndex);
            index = getParentIndex(index);
            parentIndex = getParentIndex(index);
        }
    }

    private boolean isSmaller(int firstIndex, int secondIndex) {
        return this.data.get(firstIndex).compareTo(this.data.get(secondIndex)) < 0;
    }

    private boolean hasParent(int childIndex) {
        return getParentIndex(childIndex) >= 0;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    @Override
    public E peek() {
        ensureNonEmpty();
        return this.data.get(0);
    }

    @Override
    public E poll() {
        ensureNonEmpty();
        E elementToReturn = this.data.get(0);
        Collections.swap(this.data, 0, this.size() - 1);
        this.data.remove(this.size() - 1);
        heapifyDown();
        return elementToReturn;
    }

    private void heapifyDown() {
        int index = 0;
        int swapIndex = getLeftChildIndex(index);

        while (swapIndex < this.size()) {
            int rightChildIndex = getRightChildIndex(index);

            if (rightChildIndex < this.size()) {
                if (isSmaller(rightChildIndex, swapIndex)) {
                    swapIndex = rightChildIndex;
                }
            }

            if (isSmaller(index, swapIndex)) {
                break;
            }

            Collections.swap(this.data, index, swapIndex);
            index = swapIndex;
            swapIndex = getLeftChildIndex(index);
        }
    }

    private int getLeftChildIndex(int index) {
        return (2 * index) + 1;
    }

    private int getRightChildIndex(int index) {
        return (2 * index) + 2;
    }

    @Override
    public void decrease(E element) {
        int elementIndex = this.data.indexOf(element);
        E heapElement = this.data.get(elementIndex);
        heapElement.decrease();

        this.heapifyUp(elementIndex);
    }

    private void ensureNonEmpty() {
        if (this.size() <= 0) {
            throw new IllegalStateException("The heap is empty");
        }
    }
}
