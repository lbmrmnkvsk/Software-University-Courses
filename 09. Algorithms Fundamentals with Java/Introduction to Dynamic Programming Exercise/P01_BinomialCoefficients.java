package L09_DP_Exercise;

import java.util.Scanner;

public class P01_BinomialCoefficients {
    private static long[][] memo;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());

        memo = new long[n + 1][k + 1];
        long coefficient = binomialCoefficient(n, k);
        System.out.println(coefficient);
    }

    private static long binomialCoefficient(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }

        if (memo[n][k] != 0) {
            return memo[n][k];
        }

        memo[n][k] = binomialCoefficient(n - 1, k - 1) + binomialCoefficient(n - 1, k);
        return memo[n][k];
    }
}
