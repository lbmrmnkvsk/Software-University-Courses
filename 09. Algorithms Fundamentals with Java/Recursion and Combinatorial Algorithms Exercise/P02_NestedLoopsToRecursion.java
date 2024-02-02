package L04_Recursion_and_Combinatorics_Exercise;

import java.util.Scanner;

public class P02_NestedLoopsToRecursion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        simulateNestedLoops(n, 1, new int[n]);
    }

    public static void simulateNestedLoops(int n, int currentDepth, int[] values) {
        if (currentDepth > n) {
            for (int i = 0; i < values.length; i++) {
                System.out.print(values[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <=n; i++) {
            values[currentDepth - 1] = i;
            simulateNestedLoops(n, currentDepth + 1, values);
        }
    }
}
