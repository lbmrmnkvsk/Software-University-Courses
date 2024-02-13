package L09_DP_Exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class P03_DividingPresents {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] presents = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        int totalSum = Arrays.stream(presents).sum();
        int halfSum = totalSum / 2;

        List<Integer> subset = findOptimalSubset(presents, halfSum);

        int alanSum = subset.stream().mapToInt(Integer::intValue).sum();
        int bobSum = totalSum - alanSum;
        System.out.println("Difference: " + Math.abs(alanSum - bobSum));
        System.out.println("Alan:" + alanSum + " Bob:" + bobSum);
        System.out.print("Alan takes: ");
        subset.forEach(present -> System.out.print(present + " "));
        System.out.println();
        System.out.println("Bob takes the rest.");
    }

    private static List<Integer> findOptimalSubset(int[] presents, int halfSum) {
        int[] dp = new int[halfSum + 1];
        boolean[][] used = new boolean[presents.length + 1][halfSum + 1];

        for (int i = 0; i < presents.length; i++) {
            for (int j = halfSum; j >= presents[i]; j--) {
                if (dp[j] < dp[j - presents[i]] + presents[i]) {
                    dp[j] = dp[j - presents[i]] + presents[i];
                    used[i + 1][j] = true;
                }
            }
        }

        List<Integer> subset = new ArrayList<>();
        for (int i = presents.length, j = halfSum; i > 0; i--) {
            if (used[i][j]) {
                subset.add(presents[i - 1]);
                j -= presents[i - 1];
            }
        }

        return subset;
    }
}
