package implementations;

import interfaces.AbstractTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tree<E> implements AbstractTree<E> {

    private E key;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E element, Tree<E>... children) {
        this.key = element;
        this.children = new ArrayList<>();
        for (Tree<E> child : children) {
            this.children.add(child);
            child.parent = this;
        }
    }

    @Override
    public void setParent(Tree<E> parent) {
        this.parent = parent;
    }

    @Override
    public void addChild(Tree<E> child) {
        this.children.add(child);
    }

    @Override
    public Tree<E> getParent() {
        return this.parent;
    }

    @Override
    public E getKey() {
        return this.key;
    }

    @Override
    public String getAsString() {
        StringBuilder sb = new StringBuilder();
        traverseTree(sb, 0, this);
        return sb.toString().trim();
    }

    private void traverseTree(StringBuilder sb, int indent, Tree<E> tree) {
        sb.append(getPadding(indent))
                .append(tree.getKey())
                .append(System.lineSeparator());

        for (Tree<E> child : tree.children) {
            traverseTree(sb, indent + 2, child);
        }
    }

    private String getPadding(int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= indent; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    @Override
    public List<E> getLeafKeys() {
        List<E> leafKeys = new ArrayList<>();
        List<Tree<E>> allNodes = getAllNodes();

        for (Tree<E> node : allNodes) {
            if (node.children.isEmpty()) {
                leafKeys.add(node.getKey());
            }
        }

        return leafKeys;
    }

    private List<Tree<E>> getAllNodes() {
        List<Tree<E>> allNodes = new ArrayList<>();
        addNextNode(this, allNodes);
        return allNodes;
    }

    private void addNextNode(Tree<E> tree, List<Tree<E>> list) {
        list.add(tree);

        for (Tree<E> child : tree.children) {
            addNextNode(child, list);
        }
    }
    @Override
    public List<E> getMiddleKeys() {
        return null;
    }

    @Override
    public Tree<E> getDeepestLeftmostNode() {
        return null;
    }

    @Override
    public List<E> getLongestPath() {
        return null;
    }

    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {
        return null;
    }

    @Override
    public List<Tree<E>> subTreesWithGivenSum(int sum) {
        return null;
    }
}



