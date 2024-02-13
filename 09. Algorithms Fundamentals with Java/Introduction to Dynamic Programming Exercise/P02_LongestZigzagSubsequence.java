package L09_DP_Exercise;
import java.util.*;
public class P02_LongestZigzagSubsequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] sequence = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        int[][] dp = new int[2][sequence.length];
        int[][] prev = new int[2][sequence.length];

        int result = 1;
        int lastIdx = 0;
        int[] bestSequence = {1, 0}; // {length, idx}

        // Initialize
        Arrays.fill(dp[0], 1);
        Arrays.fill(dp[1], 1);
        Arrays.fill(prev[0], -1);
        Arrays.fill(prev[1], -1);

        for (int i = 1; i < sequence.length; i++) {
            for (int j = 0; j < i; j++) {
                if (sequence[j] < sequence[i] && dp[0][j] + 1 >= dp[1][i]) {
                    dp[1][i] = dp[0][j] + 1;
                    prev[1][i] = j;
                    if (dp[1][i] > result) {
                        result = dp[1][i];
                        lastIdx = i;
                        bestSequence[0] = dp[1][i];
                        bestSequence[1] = 1;
                    }
                } else if (sequence[j] > sequence[i] && dp[1][j] + 1 >= dp[0][i]) {
                    dp[0][i] = dp[1][j] + 1;
                    prev[0][i] = j;
                    if (dp[0][i] > result) {
                        result = dp[0][i];
                        lastIdx = i;
                        bestSequence[0] = dp[0][i];
                        bestSequence[1] = 0;
                    }
                }
            }
        }

        // Reconstruct the zigzag sequence
        int[] zigzag = new int[result];
        int index = result - 1;
        while (lastIdx != -1) {
            zigzag[index--] = sequence[lastIdx];
            lastIdx = prev[bestSequence[1]][lastIdx];
            bestSequence[1] = bestSequence[1] == 0 ? 1 : 0;
        }

        // Output the length and the actual sequence

        for (int num : zigzag) {
            System.out.print(num + " ");
        }
    }
}
