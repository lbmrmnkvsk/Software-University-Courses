import java.util.function.Consumer;

public class RedBlackTree<T extends Comparable<T>> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node<T> root;

    public RedBlackTree() {
    }

    private RedBlackTree(Node<T> node) {
        this.preOrderCopy(node);
    }

    private void preOrderCopy(Node<T> node) {
        if (node == null) {
            return;
        }

        this.insert(node.value);
        this.preOrderCopy(node.left);
        this.preOrderCopy(node.right);
    }

    public int getNodesCount() {
        return this.getNodesCount(this.root);
    }

    private int getNodesCount(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return node.count;
    }

    // TODO:
    //  Insert using iteration over the nodes
    //  You can make a recursive one it is up to you
    //  The difference is that the recursive call should
    //  return Node
    public void insert(T value) {
        this.root = this.insert(value, this.root);
        this.root.color = BLACK;
    }

    private Node<T> insert(T element, Node<T> node) {
        if (node == null) {
            return new Node<>(element, RED);
        }

        if (element.compareTo(node.value) < 0) {
            node.left = this.insert(element, node.left);
        } else if (element.compareTo(node.value) > 0) {
            node.right = this.insert(element, node.right);
        }

        if (this.isRed(node.right) && !this.isRed(node.left))
            node = this.rotateLeft(node);
        if (this.isRed(node.left) && this.isRed(node.left.left))
            node = this.rotateRight(node);
        if (this.isRed(node.left) && this.isRed(node.right))
            this.flipColors(node);

        node.count = 1 + this.getNodesCount(node.left) + this.getNodesCount(node.right);
        return node;
    }

    private boolean isRed(Node<T> node) {
        if (node == null) {
            return false;
        }
        return node.color;
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> temp = node.right;
        node.right = temp.left;
        temp.left = node;
        node.color = RED;
        temp.color = BLACK;
        node.count = 1 + this.getNodesCount(node.left) + this.getNodesCount(node.right);
        return temp;
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> temp = node.left;
        node.left = temp.right;
        temp.right = node;
        temp.color = node.color;
        node.color = RED;
        node.count = 1 + this.getNodesCount(node.left) + this.getNodesCount(node.right);
        return temp;
    }

    private void flipColors(Node<T> node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    public boolean contains(T value) {
        return this.findElement(value) != null;
    }

    public RedBlackTree<T> search(T item) {
        return new RedBlackTree<>(this.findElement(item));
    }

    private Node<T> findElement(T item) {
        Node<T> current = this.root;
        while (current != null) {
            if (item.compareTo(current.value) < 0) {
                current = current.left;
            } else if (item.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                break;
            }
        }
        return current;
    }

    public void eachInOrder(Consumer<T> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node<T> node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, consumer);
        consumer.accept(node.value);
        this.eachInOrder(node.right, consumer);
    }

    public static class Node<T extends Comparable<T>> {
        private T value;
        private Node<T> left;
        private Node<T> right;
        private boolean color;
        private int count;

        private boolean isRed(Node<T> node) {
            if (node == null) {
                return false;
            }
            return node.color;
        }

        public Node(T value) {
            this.count = 1;
            this.value = value;
            this.color = RED;
        }

        public Node(T value, boolean color) {
            this.count = 1;
            this.value = value;
            this.color = color;
        }
    }
}

