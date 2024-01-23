package L03_CombinatorialProblems;

import java.util.Scanner;

public class P07_NchooseKcount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());

        long result = calculateCombinations(n, k);
        System.out.println(result);
    }

    public static long calculateCombinations(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        } else {
            return calculateCombinations(n - 1, k - 1) + calculateCombinations(n - 1, k);
        }
    }
}
