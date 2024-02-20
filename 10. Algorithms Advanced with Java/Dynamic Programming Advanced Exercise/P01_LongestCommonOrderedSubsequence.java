package L09_DP_Advanced_Exercise;

import java.util.Scanner;

public class P01_LongestCommonOrderedSubsequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String first = scanner.nextLine();
        String second = scanner.nextLine();
        int[][] lcos = new int[first.length() + 1][second.length() + 1];
        int maxLength = 0;
        int endIndex = -1;

        for (int i = 1; i <= first.length(); i++) {
            for (int j = 1; j <= second.length(); j++) {
                if (first.charAt(i - 1) == second.charAt(j - 1)) {
                    lcos[i][j] = lcos[i - 1][j - 1] + 1;
                    if (lcos[i][j] > maxLength) {
                        maxLength = lcos[i][j];
                        endIndex = i;
                    }
                }
            }
        }

        String result = first.substring(endIndex - maxLength, endIndex);
        System.out.println(result);
    }
}
