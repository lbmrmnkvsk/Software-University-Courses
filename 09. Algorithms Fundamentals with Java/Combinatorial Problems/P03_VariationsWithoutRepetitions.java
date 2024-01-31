package L03_CombinatorialProblems;

import java.util.Scanner;

public class P03_VariationsWithoutRepetitions {
    private static String[] elements;
    private static String[] variations;
    private static boolean[] used;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        elements = scanner.nextLine().split("\\s+");
        int k = Integer.parseInt(scanner.nextLine());
        variations = new String[k];
        used = new boolean[elements.length];

        generateVariations(0);
    }

    public static void generateVariations(int index) {
        if (index == variations.length) {
            System.out.println(String.join(" ", variations));
        } else {
            for (int i = 0; i < elements.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    variations[index] = elements[i];
                    generateVariations(index + 1);
                    used[i] = false; // backtrack
                }
            }
        }

    }
}
