package solutions;

public class Pair {
    private int first;
    private int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getKey() {
        return this.first;
    }

    public int getValue() {
        return this.second;
    }
}
