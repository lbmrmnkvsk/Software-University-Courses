package solutions;

import com.sun.source.tree.Tree;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class BinaryTree {
    private int key;
    private BinaryTree left;
    private BinaryTree right;

    public BinaryTree(int key, BinaryTree first, BinaryTree second) {
        this.key = key;
        this.left = first;
        this.right = second;
    }

    public Integer findLowestCommonAncestor(int first, int second) {
        List<Integer> firstPath = findPath(this, first);
        List<Integer> secondPath = findPath(this, second);

        int smallerSize = 0;
        if (firstPath.size() <= secondPath.size()) {
            smallerSize = firstPath.size();
        } else {
            smallerSize = secondPath.size();
        }

        int i = 0;
        for (; i < smallerSize; i++) {
            if (!firstPath.get(i).equals(secondPath.get(i))) {
                break;
            }
        }

        return firstPath.get(i - 1);
    }

    private List<Integer> findPath(BinaryTree tree,int element) {
        List<Integer> result = new ArrayList<>();
        findNodePath(tree, element, result);
        return result;
    }

    private boolean findNodePath(BinaryTree tree, int element, List<Integer> currentPath) {
        if (tree == null) {
            return false;
        }

        if (tree.key == element) {
            return true;
        }

        currentPath.add(tree.key);

        boolean leftResult = findNodePath(tree.left, element, currentPath);
        if (leftResult) {
            return true;
        }

        boolean rightResult = findNodePath(tree.right, element, currentPath);
        if (rightResult) {
            return true;
        }

        currentPath.remove(Integer.valueOf(tree.key));
        return false;
    }

    public List<Integer> topView() {
        Map<Integer, Pair> offsetToValueLevel = new TreeMap<>();
        traverseTree(this, 0, 1, offsetToValueLevel);

        return offsetToValueLevel.values()
                .stream()
                .map(Pair::getKey)
                .collect(Collectors.toList());
    }

    private void traverseTree(BinaryTree tree, int offset, int level, Map<Integer, Pair> offsetToValueLevel) {
        if (tree == null) {
            return;
        }

        Pair currentValueLevel = offsetToValueLevel.get(offset);

        if (currentValueLevel == null || level < currentValueLevel.getValue()) {
            offsetToValueLevel.put(offset, new Pair(tree.key, level));
        }

        traverseTree(tree.left, offset - 1, level + 1, offsetToValueLevel);
        traverseTree(tree.right, offset + 1, level + 1, offsetToValueLevel);
    }
}
