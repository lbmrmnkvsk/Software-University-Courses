package L09_DP_Exercise;

import java.util.Arrays;
import java.util.Scanner;

public class P04_SumWithUnlimitedAmountOfCoins {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] coins = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        int targetSum = Integer.parseInt(scanner.nextLine());

        int[] ways = new int[targetSum + 1];
        ways[0] = 1;

        for (int coin : coins) {
            for (int sum = coin; sum <= targetSum; sum++) {
                ways[sum] += ways[sum - coin];
            }
        }

        System.out.println(ways[targetSum]);
    }
}
