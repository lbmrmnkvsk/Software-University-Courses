package L03_CombinatorialProblems;

import java.util.Scanner;

public class P05_CombinationsWithoutRepetition {
    private static String[] elements;
    private static String[] combination;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        elements = scanner.nextLine().split("\\s+");
        int k = Integer.parseInt(scanner.nextLine());
        combination = new String[k];

        generateCombinations(0, 0);
    }

    public static void generateCombinations(int index, int start) {
        if (index == combination.length) {
            System.out.println(String.join(" ", combination));
        } else {
            for (int i = start; i < elements.length; i++) {
                combination[index] = elements[i];
                generateCombinations(index + 1, i + 1);
            }
        }
    }
}
