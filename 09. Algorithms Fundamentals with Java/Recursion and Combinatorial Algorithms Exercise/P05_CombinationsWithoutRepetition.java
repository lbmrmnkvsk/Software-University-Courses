package L04_Recursion_and_Combinatorics_Exercise;

import java.util.Scanner;

public class P05_CombinationsWithoutRepetition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        combinationsWithoutRepetition(n, k);
    }

    public static void combinationsWithoutRepetition(int n, int k) {
        int[] elements = new int[n];
        for (int i = 1; i <= n; i++) {
            elements[i - 1] = i;
        }
        int[] combination = new int[k];
        generateCombinations(elements, combination, 0, 0);
    }

    private static void generateCombinations(int[] elements, int[] combination, int index, int start) {
        if (index == combination.length) {
            printCombination(combination);
            return;
        }

        for (int i = start; i < elements.length; i++) {
            combination[index] = elements[i];
            generateCombinations(elements, combination, index + 1, i + 1);
        }
    }

    private static void printCombination(int[] combination) {
        for (int value : combination) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
