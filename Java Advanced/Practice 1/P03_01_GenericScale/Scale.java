package P03_01_GenericScale;

public class Scale <T extends Comparable<T>> {
    private T left;
    private T right;

    public Scale(T left, T right) {
        this.left = left;
        this.right = right;
    }

    public T getHeavier() {
        if (left.compareTo(right) < 0) {
            return right;
        } else if (this.left.compareTo(this.right) > 0) {
            return this.left;
        } else {
            return null;
        }
    }
}
