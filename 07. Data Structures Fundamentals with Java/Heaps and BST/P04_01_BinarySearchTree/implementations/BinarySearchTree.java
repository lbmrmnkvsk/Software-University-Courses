package implementations;

import interfaces.AbstractBinarySearchTree;

public class BinarySearchTree<E extends Comparable<E>> implements AbstractBinarySearchTree<E> {
    private Node<E> root;
    private Node<E> left;
    private Node<E> right;

    public BinarySearchTree() {

    }

    public BinarySearchTree(Node<E> root) {
        this.copy(root);
    }

    private void copy(Node<E> node) {
        if (node != null) {
            this.insert(node.value);
            this.copy(node.leftChild);
            this.copy(node.rightChild);
        }
    }

    @Override
    public void insert(E element) {
        Node<E> newNode = new Node<>(element);
        if (this.getRoot() == null) {
            this.root = newNode;
        } else {
            Node<E> current = this.root;

            while (current != null && (current.leftChild != null || current.rightChild != null) ) {
                if (isLess(element, current.value)) {
                    current = current.leftChild;
                } else if (isLarger(element, current.value)) {
                    current = current.rightChild;
                } else {
                    return;
                }
            }

            if (current != null) {
                if (isLess(element, current.value)) {
                    current.leftChild = newNode;
                } else if (isLarger(element, current.value)) {
                    current.rightChild = newNode;
                }
            }

        }

    }

    private boolean isLess(E first, E second) {
        return first.compareTo(second) < 0;
    }

    private boolean isLarger(E first, E second) {
        return first.compareTo(second) > 0;
    }

    private boolean isEqual(E first, E second) {
        return first.compareTo(second) == 0;
    }

    @Override
    public boolean contains(E element) {
        Node<E> current = this.root;

        while (current != null) {
            if (isLess(element, current.value)) {
                current = current.leftChild;
            } else if (isLarger(element, current.value)) {
                current = current.rightChild;
            } else if (isEqual(element, current.value)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public AbstractBinarySearchTree<E> search(E element) {
        AbstractBinarySearchTree<E> result = null;

        Node<E> current = this.root;

        while (current != null) {
            if (isLess(element, current.value)) {
                current = current.leftChild;
            } else if (isLarger(element, current.value)) {
                current = current.rightChild;
            } else if (isEqual(element, current.value)) {
                return new BinarySearchTree<>(current); // TODO: return this.copy(this.getValue());
            }
        }

        return null;
    }

    @Override
    public Node<E> getRoot() {
        return this.root;
    }

    @Override
    public Node<E> getLeft() {
        return this.left;
    }

    @Override
    public Node<E> getRight() {
        return this.right;
    }

    @Override
    public E getValue() {
        return this.root.value;
    }
}
