package main;

import java.util.*;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Hierarchy<T> implements IHierarchy<T> {

    private Map<T, List<T>> parentChilds;
    private Map<T, T> childParent;


    public Hierarchy(T element) {
        this.parentChilds = new LinkedHashMap<>();
        this.childParent = new LinkedHashMap<>();

        this.childParent.put(element, null);
        this.parentChilds.put(element,new ArrayList<>());
    }

    @Override
    public void add(T parent, T child) {

        throwExceptionIfNonExistent(parent);

        throwExceptionIfDuplicate(child);

        this.childParent.put(child, parent);

        List<T> children = this.parentChilds.get(parent);

        if (children == null) {
            children = new ArrayList<>();
        }

        children.add(child);

        this.parentChilds.put(parent, children);
    }

    @Override
    public int getCount() {
        return childParent.size();
    }

    @Override
    public void remove(T element) {
        throwExceptionIfNonExistent(element);

        T parent = childParent.get(element);

        if (parent == null) {
            throw new IllegalStateException();
        }

        List<T> elementChildren = parentChilds.get(element);

        if (elementChildren == null) {
            elementChildren = new ArrayList<>();
        }

        for (T child : elementChildren) {
            childParent.put(child,parent);
        }

        List<T> parentChildren = parentChilds.get(parent);

        parentChildren.remove(element);

        parentChildren.addAll(elementChildren);

        parentChilds.put(parent, parentChildren);

        parentChilds.remove(element);
        childParent.remove(element);
    }

    @Override
    public boolean contains(T element) {
        return childParent.containsKey(element);
    }

    @Override
    public T getParent(T element) {

        throwExceptionIfNonExistent(element);

        return this.childParent.get(element);
    }

    @Override
    public Iterable<T> getChildren(T element) {

        throwExceptionIfNonExistent(element);

        List<T> result = this.parentChilds.get(element);

        if (result == null) {
            result = new ArrayList<>();
        }

        return result;
    }

    @Override
    public Iterable<T> getCommonElements(IHierarchy<T> other) {

        List<T> result = new ArrayList<>();

        for (T element : this.childParent.keySet()) {
            if (other.contains(element)) {
                result.add(element);
            }
        }

        return result;
    }

    private void throwExceptionIfDuplicate(T element) {
        if (this.contains(element)) {
            throwDefaultException();
        }
    }

    private void throwExceptionIfNonExistent(T element) {
        if (!this.contains(element)) {
            throwDefaultException();
        }
    }

    private void throwDefaultException() {
        throw new IllegalArgumentException();
    }

    private T getRoot() {

        return this.childParent.entrySet()
                .stream()
                .filter(x -> x.getValue() == null)
                .map(Map.Entry::getKey)
                .findFirst()
                .get();
    }

    @Override
    public Iterator<T> iterator() {
        return new BFS();
    }

    private class BFS implements Iterator<T> {

        Queue<T> ordered;

        public BFS() {
            this.ordered = new ArrayDeque<>();

            Queue<T> temp = new ArrayDeque<>();

            temp.offer(getRoot());

            while (temp.size() > 0) {

                T current = temp.poll();

                for (T child : getChildren(current)) {
                    temp.offer(child);
                }

                this.ordered.offer(current);
            }
        }

        @Override
        public boolean hasNext() {

            return this.ordered.size() > 0;
        }

        @Override
        public T next() {
            return this.ordered.poll();
        }
    }
}
