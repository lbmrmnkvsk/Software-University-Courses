package L03_CombinatorialProblems;

import java.util.Scanner;

public class P01_CG {
    public static String[] elements;
    public static String[] permutes;
    public static boolean[] used;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        elements = scanner.nextLine().split("\\s+");
        permutes = new String[elements.length];
        used = new boolean[elements.length];

        permute(0);
    }

    public static void permute(int index) {
        if (index == elements.length) {
            print(permutes);
            return;
        }

        for (int i = 0; i < elements.length; i++) {
            if (!used[i]) {
                used[i] = true;
                permutes[index] = elements[i];
                permute(index + 1);
                used[i] = false; // backtrack
            }
        }
    }

    public static void print(String[] permutes) {
        System.out.println(String.join(" ", permutes));
    }
}
