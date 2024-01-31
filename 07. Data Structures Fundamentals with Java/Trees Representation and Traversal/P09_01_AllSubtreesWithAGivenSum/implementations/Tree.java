package implementations;

import interfaces.AbstractTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    private List<Tree<E>> getAllSubtreeNodes(Tree<E> start) {
        List<Tree<E>> allNodes = new ArrayList<>();
        addNextNode(start, allNodes);
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
        List<E> middleKeys = new ArrayList<>();
        List<Tree<E>> allNodes = getAllNodes();

        for (Tree<E> node : allNodes) {
            if (node.parent != null && !node.children.isEmpty()) {
                middleKeys.add(node.getKey());
            }
        }

        return middleKeys;
    }

    @Override
    public Tree<E> getDeepestLeftmostNode() {
        List<Tree<E>> allNodes = getAllNodes();
        int level = 1;
        Tree<E> deepestNode = this;

        for (Tree<E> node : allNodes) {
            Tree<E> currentNode = node;
            int currentLevel = 1;
            if (currentNode.children.isEmpty()) {
                while (currentNode.parent != null) {
                    currentNode = currentNode.parent;
                    currentLevel++;
                }

                if (currentLevel > level) {
                    level = currentLevel;
                    deepestNode = node;
                }
            }
        }

        return deepestNode;
    }



    @Override
    public List<E> getLongestPath() {
        List<Tree<E>> allNodes = getAllNodes();
        int maxPathLength = 1;
        List<E> longestPath = Arrays.asList(this.key);


        for (Tree<E> node : allNodes) {
            if (node.children.isEmpty()) {
                List<E> currentPath = new ArrayList<>();
                currentPath.add(node.getKey());
                Tree<E> currentLeaf = node;

                while (currentLeaf.parent != null) {
                    currentLeaf = currentLeaf.parent;
                    currentPath.add(currentLeaf.getKey());
                }

                if (maxPathLength < currentPath.size()) {
                    longestPath = currentPath;
                    maxPathLength = currentPath.size();
                }
            }
            }

        Collections.reverse(longestPath);
        return longestPath;
    }

    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {
        List<List<E>> pathsWithGivenSum = new ArrayList<>();
        List<Tree<E>> allNodes = getAllNodes();

        for (Tree<E> node : allNodes) {
            if (node.children.isEmpty()) {
                List<E> currentPath = new ArrayList<>();
                currentPath.add(node.key);
                Tree<E> currentNode = node;

                while (currentNode.parent != null) {
                    currentNode = currentNode.parent;
                    currentPath.add(currentNode.key);
                }

                int currentSum = 0;
                for (E element : currentPath) {
                    currentSum += (int) element;
                }

                if (currentSum == sum) {
                    Collections.reverse(currentPath);
                    pathsWithGivenSum.add(currentPath);
                }
            }
        }

        return pathsWithGivenSum;
    }

    @Override
    public List<Tree<E>> subTreesWithGivenSum(int sum) {
        List<Tree<E>> allNodes = getAllSubtreeNodes(this);
        List<Tree<E>> result = new ArrayList<>();

        for (Tree<E> node : allNodes) {
            List<Tree<E>> subtree = getAllSubtreeNodes(node);
            int currentSum = 0;

            for (Tree<E> currentNode : subtree) {
                currentSum += (int) currentNode.key;
            }

            if (currentSum == sum) {
                result.add(node);
            }
        }

        return result;
    }
}



