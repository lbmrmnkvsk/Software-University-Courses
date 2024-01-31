import solutions.BinaryTree;

import java.util.ArrayList;
import java.util.function.Consumer;

import java.util.List;

public class BinarySearchTree<E extends Comparable<E>> {
    private Node<E> root;

    public BinarySearchTree() {

    }

    public BinarySearchTree(E element) {
        this.root = new Node<>(element);
    }

    public BinarySearchTree(Node<E> otherRoot) {
        this.root = new Node<>(otherRoot);

    }
    public static class Node<E> {
        private E value;
        private Node<E> leftChild;
        private Node<E> rightChild;

        private int count;

        public Node(Node<E> other) {
            this.value = other.value;
            this.count = other.count;

            if (other.getLeft() != null) {
                this.leftChild = new Node<>(other.getLeft());
            }

            if (other.getRight() != null) {
                this.rightChild = new Node<>(other.getRight());
            }
        }

		public Node(E value) {
            this.value = value;
            this.count = 1;
        }

        public Node<E> getLeft() {
            return this.leftChild;
        }

        public Node<E> getRight() {
            return this.rightChild;
        }

        public E getValue() {
            return this.value;
        }
    }
	
	public void eachInOrder(Consumer<E> consumer) {
        nodeInOrder(this.root, consumer);
    }

    private void nodeInOrder(Node<E> node, Consumer<E> consumer) {
        if (node != null) {
            nodeInOrder(node.getLeft(), consumer);
            consumer.accept(node.getValue());
            nodeInOrder(node.getRight(), consumer);
        }
    }

    public Node<E> getRoot() {
        return this.root;
    }

    public void insert(E element) {
        if (this.root == null) {
            this.root = new Node<>(element);
        } else {
            Node<E> current = this.root;
            List<Node<E>> passedNodes = new ArrayList<>();

            while (current.getLeft() != null || current.getRight() != null) {
                if (isSmaller(element, current.getValue()) && current.getLeft() != null) {
                    passedNodes.add(current);
                    current = current.getLeft();
                } else if (isLarger(element, current.getValue()) && current.getRight() != null) {
                    passedNodes.add(current);
                    current = current.getRight();
                } else if (isEqual(element, current.getValue())) {
                    return;
                } else {
                    break;
                }
            }

            passedNodes.add(current);
            for (Node<E> node : passedNodes) {
                node.count++;
            }

            if (isSmaller(element, current.getValue())) {
                current.leftChild = new Node<>(element);
            } else if (isLarger(element, current.getValue())) {
                current.rightChild = new Node<>(element);
            }
        }
    }

    private boolean isSmaller(E first, E second) {
        return first.compareTo(second) < 0;
    }

    private boolean isEqual(E first, E second) {
        return first.compareTo(second) == 0;
    }

    private boolean isLarger(E first, E second) {
        return first.compareTo(second) > 0;
    }

    public boolean contains(E element) {
        Node<E> current = this.root;
        if (isEqual(element, current.getValue())) {
            return true;
        }

        while (current.getLeft() != null || current.getRight() !=null) {
            if (isSmaller(element, current.getValue())) {
                current = current.getLeft();
            } else if (isLarger(element, current.getValue())) {
                current = current.getRight();
            } else {
                return true;
            }
        }

        return isEqual(element, current.getValue());
    }
    public BinarySearchTree<E> search(E element) {
        Node<E> found = containsNode(this.root, element);

        if (found == null) {
            return null;
        } else {
            return new BinarySearchTree<>(found);
        }
    }

    private Node<E> containsNode(Node<E> node, E element) {
        if (node == null) {
            return null;
        }

        if (isEqual(element, node.getValue())) {
            return node;
        } else if (isSmaller(element, node.getValue())) {
            return containsNode(node.getLeft(), element);
        } else {
            return containsNode(node.getRight(), element);
        }
    }

    public List<E> range(E first, E second) {
      return elementsInRange(this.root, first, second);
    }

    private List<E> elementsInRange(Node<E> node, E first, E second) {
        if (node == null) {
            return null;
        }

        List<E> result = new ArrayList<>();
        if (isSmaller(second, node.getValue()) && isLarger(first, node.getValue())) {
            result.add(node.getValue());
        } else if (isEqual(first, node.getValue()) || isEqual(second, node.getValue())) {
            result.add(node.getValue());
        }

        if (node.getLeft() != null) {
            result.addAll(elementsInRange(node.getLeft(), first, second));
        }

        if (node.getRight() != null) {
            result.addAll(elementsInRange(node.getRight(), first, second));
        }

        return result;
    }

    public void deleteMin() {
        ensureNonEmpty();
        Node<E> current = this.root;

        if (current.getLeft() == null) {
            this.root = this.root.getRight();
            return;
        }

        while (current.getLeft().getLeft() != null) {
            current.count--;
            current = current.getLeft();
        }

        current.count--;
        current.leftChild = current.getLeft().getRight();
    }

    private void ensureNonEmpty() {
        if (this.root == null) {
            throw new IllegalArgumentException("The tree is empty");
        }
    }

    public void deleteMax() {
        ensureNonEmpty();
        Node<E> current = this.root;

        if (current.getRight() == null) {
            this.root = this.root.getLeft();
        }

        while (current.getRight().getRight() != null) {
            current.count--;
            current = current.getRight();
        }

        current.count--;
        current.rightChild = current.getRight().getLeft();
    }

    public int count() {
        if (this.root == null) {
            return 0;
        } else {
            return this.root.count;
        }
    }

    public int rank(E element) {
        return nodeRank(this.root, element);
    }

    private int nodeRank(Node<E> node, E element) {
        if (node == null) {
            return 0;
        }

        if (isSmaller(element, node.getValue())) {
            return nodeRank(node.getLeft(), element);
        } else if (isEqual(element, node.getValue())) {
            return node.getLeft().count;
        }

        return node.getLeft().count + 1 + nodeRank(node.getRight(), element);
    }

    public E ceil(E element) {
        if (this.root == null) {
            return null;
        }

        Node<E> current = this.root;
        Node<E> nearestLarger = null;

        while (current != null) {
            if (isSmaller(element, current.getValue())) {
                nearestLarger = current;
                current = current.getLeft();
            } else if (isLarger(element, current.getValue())) {
                current = current.getRight();
            } else {
                Node<E> right = current.getRight();
                if (right != null && nearestLarger != null) {
                    nearestLarger = isSmaller(right.getValue(), nearestLarger.getValue()) ? right : nearestLarger;
                } else if (nearestLarger == null) {
                    nearestLarger = right;
                }

                break;
            }
        }

        return nearestLarger == null ? null : nearestLarger.getValue();
    }

    public E floor(E element) {
        if (this.root == null) {
            return null;
        }

        Node<E> current = this.root;
        Node<E> nearestSmaller = null;

        while (current != null) {
            if (isLarger(element, current.getValue())) {
                nearestSmaller = current;
                current = current.getRight();
            } else if (isSmaller(element, current.getValue())) {
                current = current.getLeft();
            } else {
                Node<E> left = current.getLeft();
                if (left != null && nearestSmaller != null) {
                    nearestSmaller = isLarger(left.getValue(), nearestSmaller.getValue()) ? left : nearestSmaller;
                } else if (nearestSmaller == null) {
                    nearestSmaller = left;
                }

                break;
            }
        }

        return nearestSmaller == null ? null : nearestSmaller.getValue();
    }
}
