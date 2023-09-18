package implementations;

import interfaces.AbstractTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Tree<E> implements AbstractTree<E> {
    private E value;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E element, Tree<E>... children) {
        this.value = element;
        this.children = new ArrayList<>();
        for (Tree<E> child : children) {
            this.children.add(child);
            child.parent = this;
        }
    }

    @Override
    public List<E> orderBfs() {
        List<E> result = new ArrayList<>();
        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);
        while (!queue.isEmpty()) {
            Tree<E> current = queue.poll();
            result.add(current.value);
            for (Tree<E> child : current.children) {
                queue.offer(child);
            }
        }

        return result;
    }

    @Override
    public List<E> orderDfs() {
        List<E> result = new ArrayList<>();
        this.dfs(this, result);
        return result;
    }

    private void dfs(Tree<E> tree, List<E> result) {
        for (Tree<E> child : tree.children) {
            this.dfs(child, result);
        }
        result.add(tree.value);
    }

    @Override
    public void addChild(E parentKey, Tree<E> child) {
        Tree<E> parent = find(this, parentKey);

        if (parent == null) {
            throw new IllegalArgumentException();
        }

        parent.children.add(child);
        child.parent = parent;
    }

    private Tree<E> find(Tree<E> current, E parentKey) {
        if (current.value.equals(parentKey)) {
            return current;
        }

        for (Tree<E> child : current.children) {
            Tree<E> found = this.find(child, parentKey);
            if (found != null) {
                return found;
            }
        }

        return null;
    }
	
	@Override
    public void removeNode(E nodeKey) {
        Tree<E> node = find(this, nodeKey);
        if (node == null) {
            throw new IllegalArgumentException();
        }

        for (Tree<E> child : node.children) {
            child.parent = null;
        }
        node.children.clear();

        if (node.parent != null) {
            node.parent.children.remove(node);
            node.parent = null;
        }

        node.value = null;
    }

    @Override
    public void swap(E firstKey, E secondKey) {
        Tree<E> firstNode = find(this, firstKey);
        Tree<E> secondNode = find(this, secondKey);

        if (firstNode == null || secondNode == null) {
            throw new IllegalArgumentException();
        }

        Tree<E> firstParent = firstNode.parent;
        Tree<E> secondParent = secondNode.parent;

        if (firstParent == null) {
            swapRoot(secondNode);
            return;
        }
        if (secondParent == null) {
            swapRoot(firstNode);
            return;
        }

        firstNode.parent = secondParent;
        secondNode.parent = firstParent;

        int firstIndex = firstParent.children.indexOf(firstNode);
        int secondIndex = secondParent.children.indexOf(secondNode);

        firstParent.children.set(firstIndex, secondNode);
        secondParent.children.set(secondIndex, firstNode);
    }

    private void swapRoot(Tree<E> node) {
        this.value = node.value;
        this.children = node.children;
        node.parent = null;
    }
}



