package L09_DP_Exercise;

import java.util.Scanner;

public class P06_WordDifferences {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String first = scanner.nextLine();
        String second = scanner.nextLine();

        System.out.println("Deletions and Insertions: " + findMinOperations(first, second));
    }

    private static int findMinOperations(String first, String second) {
        int[][] dp = new int[first.length() + 1][second.length() + 1];

        for (int i = 0; i <= first.length(); i++) {
            for (int j = 0; j <= second.length(); j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (first.charAt(i - 1) != second.charAt(j - 1)) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }

        int lcs = dp[first.length()][second.length()];

        return (first.length() - lcs) + (second.length() - lcs);
    }
}
