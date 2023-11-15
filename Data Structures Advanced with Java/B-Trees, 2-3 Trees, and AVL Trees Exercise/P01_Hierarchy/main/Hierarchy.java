package main;

import java.util.*;
import java.util.stream.Collectors;

public class Hierarchy<T> implements IHierarchy<T> {
    private Map<T, HierarchyNode<T>> data;
    private HierarchyNode<T> root;

    public Hierarchy(T element) {
        this.data = new HashMap<>();
        HierarchyNode<T> root = new HierarchyNode<>(element);
        this.data.put(element, root);
        this.root = root;
    }

    @Override
    public int getCount() {
        return this.data.size();
    }

    @Override
    public void add(T element, T child) {
        if (!this.data.containsKey(element)) {
            throw new IllegalArgumentException();
        }

        if (this.data.containsKey(child)) {
            throw new IllegalArgumentException();
        }

        HierarchyNode<T> childNode = new HierarchyNode<>(child);
        HierarchyNode<T> parentNode = this.data.get(element);

        this.data.put(child, childNode);
        childNode.setParent(parentNode);
        parentNode.getChildren().add(childNode);
    }

    @Override
    public void remove(T element) {
        HierarchyNode<T> toRemove = this.data.get(element);
        if (toRemove == null || toRemove.equals(this.root)) {
            throw new IllegalArgumentException();
        }

        HierarchyNode<T> parent = toRemove.getParent();
        this.data.remove(element);
        parent.getChildren().remove(toRemove);
        parent.getChildren().addAll(toRemove.getChildren());
        toRemove.setParent(null);
    }

    @Override
    public Iterable<T> getChildren(T element) {
        HierarchyNode<T> node = this.data.get(element);
        if (node == null) {
            throw new IllegalArgumentException();
        }

        return node.getChildren().stream().map(n -> n.getValue()).collect(Collectors.toList());
    }

    @Override
    public T getParent(T element) {
        HierarchyNode<T> node = this.data.get(element);
        if (node == null) {
            throw new IllegalArgumentException();
        }

        if (node.getParent() == null) {
            return null;
        } else {
            return node.getParent().getValue();
        }
    }

    @Override
    public boolean contains(T element) {
        return this.data.containsKey(element);
    }

    @Override
    public Iterable<T> getCommonElements(IHierarchy<T> other) {
        List<T> result = new ArrayList<>();

        this.data.keySet().forEach(k -> {
            if (other.contains(k)) {
                result.add(k);
            }
        });

        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Deque<HierarchyNode<T>> queue = new ArrayDeque<>(Collections.singletonList(root));

            @Override
            public boolean hasNext() {
                return queue.size() > 0;
            }

            @Override
            public T next() {
                HierarchyNode<T> nextElement = queue.poll();
                for (HierarchyNode<T> child : nextElement.getChildren()) {
                    queue.offer(child);
                }
                return nextElement.getValue();
            }
        };
    }
}
