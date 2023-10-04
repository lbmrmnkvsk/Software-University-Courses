package solutions;

import java.util.ArrayList;
import java.util.List;

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
        return null;
    }
}
