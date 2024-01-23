package L03_CombinatorialProblems;

import java.util.Scanner;

public class P04_VariationsWithRepetition {
    private static String[] elements;
    private static String[] variations;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        elements = scanner.nextLine().split("\\s+");
        int k = Integer.parseInt(scanner.nextLine());
        variations = new String[k];

        generateVariations(0);
    }

    public static void generateVariations(int index) {
        if (index == variations.length) {
            System.out.println(String.join(" ", variations));
        } else {
            for (String element : elements) {
                variations[index] = element;
                generateVariations(index + 1);
            }
        }
    }
}
