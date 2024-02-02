package L04_Recursion_and_Combinatorics_Exercise;

import java.util.Scanner;

public class P03_CombinationsWithRepetition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());

        generateCombinationsWithRepetition(n, k, new int[k], 1);
    }

    public static void generateCombinationsWithRepetition(int n, int k, int[] combination, int start) {
        if (k == 0) {
            for (int num : combination) {
                System.out.print(num + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i <= n; i++) {
            combination[combination.length - k] = i;
            generateCombinationsWithRepetition(n, k - 1, combination, i);
        }
    }
}
