package L04_Recursion_and_Combinatorics_Exercise;

import java.util.Scanner;
import java.util.Stack;

public class P04_TowerOfHanoi {
    public static Stack<Integer> source = new Stack<>();
    public static Stack<Integer> destination = new Stack<>();
    public static Stack<Integer> spare = new Stack<>();
    public static int steps = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = n; i > 0; i--) {
            source.push(i);
        }

        printRods();

        moveDisks(n, source, destination, spare);

        printRods();
    }

    public static void moveDisks(int n, Stack<Integer> source, Stack<Integer> destination, Stack<Integer> spare) {
        if (n == 1) {
            destination.push(source.pop());
            steps++;
            printRods();
        } else {
            moveDisks(n - 1, source, spare, destination);
            moveDisk(source, destination);
            moveDisks(n - 1, spare, destination, source);
        }
    }

    public static void moveDisk(Stack<Integer> source, Stack<Integer> destination) {
        destination.push(source.pop());
        steps++;
        printRods();
    }

    public static void printRods() {
        System.out.println("Step #" + steps + ": Moved disk");
        System.out.println("Source: " + source);
        System.out.println("Destination: " + destination);
        System.out.println("Spare: " + spare);
        System.out.println();
    }
}
