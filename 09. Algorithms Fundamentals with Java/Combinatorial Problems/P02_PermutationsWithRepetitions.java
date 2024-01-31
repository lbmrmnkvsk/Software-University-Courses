package L03_CombinatorialProblems;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class P02_PermutationsWithRepetitions {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] elements = scanner.nextLine().split("\\s+");
        Set<String> seen = new HashSet<>();
        permute(elements, 0, seen);
    }

    public static void permute(String[] elements, int index, Set<String> seen) {
        if (index == elements.length) {
            String permutation = String.join(" ", elements);
            if (!seen.contains(permutation)) {
                seen.add(permutation);
                System.out.println(permutation);
            }
        } else {
            for (int i = index; i < elements.length; i++) {
                swap(elements, index, i);
                permute(elements, index + 1, seen);
                swap(elements, index, i);
            }
        }
    }

    private static void swap(String[] elements, int firstIndex, int secondIndex) {
        String temp = elements[firstIndex];
        elements[firstIndex] = elements[secondIndex];
        elements[secondIndex] = temp;
    }

}
