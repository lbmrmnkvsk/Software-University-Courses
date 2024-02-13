package L09_DP_Exercise;

import java.util.Arrays;
import java.util.Scanner;

public class P05_SumWithLimitedAmountOfCoins {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] coins = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        int targetSum = Integer.parseInt(scanner.nextLine());

        int[] combinationsCount = new int[targetSum + 1];

        for (int sum = 0; sum <= targetSum; sum++) {
            if (sum % coins[0] == 0) {
                combinationsCount[sum] = 1;
            }
        }

        for (int i = 1; i < coins.length; i++) {
            for (int sum = coins[i]; sum <= targetSum; sum++) {
                combinationsCount[sum] += combinationsCount[sum - coins[i]];
            }
        }

        System.out.println(combinationsCount[targetSum]);
    }
}
