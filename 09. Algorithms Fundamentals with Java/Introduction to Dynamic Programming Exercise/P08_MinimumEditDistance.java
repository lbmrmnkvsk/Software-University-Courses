package L09_DP_Exercise;
import java.util.*;
public class P08_MinimumEditDistance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int replaceCost = Integer.parseInt(scanner.nextLine());
        int insertCost = Integer.parseInt(scanner.nextLine());
        int deleteCost = Integer.parseInt(scanner.nextLine());

        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();
        scanner.close();

        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j * insertCost;
                } else if (j == 0) {
                    dp[i][j] = i * deleteCost;
                } else {
                    int cost = s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : replaceCost;
                    dp[i][j] = min(
                            dp[i - 1][j - 1] + cost,  // replace
                            dp[i - 1][j] + deleteCost, // delete
                            dp[i][j - 1] + insertCost  // insert
                    );
                }
            }
        }

        System.out.println("Minimum edit distance: " + dp[s1.length()][s2.length()]);
    }

    private static int min(int... numbers) {
        int minimum = Integer.MAX_VALUE;
        for (int number : numbers) {
            if (number < minimum) {
                minimum = number;
            }
        }
        return minimum;
    }
}
