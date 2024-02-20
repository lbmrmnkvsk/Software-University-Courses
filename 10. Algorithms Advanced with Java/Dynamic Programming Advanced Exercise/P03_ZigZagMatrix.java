package L09_DP_Advanced_Exercise;
import java.util.*;
public class P03_ZigZagMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = Integer.parseInt(scanner.nextLine());
        int cols = Integer.parseInt(scanner.nextLine());

        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            String[] elements = scanner.nextLine().split(",");
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Integer.parseInt(elements[j]);
            }
        }

        int[][] dp = new int[rows][cols];
        int[][] path = new int[rows][cols];

        // Initialize first column
        for (int i = 0; i < rows; i++) {
            dp[i][0] = matrix[i][0];
        }

        // Fill in the dp table and keep the path
        for (int c = 1; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                int previousMax = 0;
                if (c % 2 == 1) {
                    // Move up
                    for (int i = r + 1; i < rows; i++) {
                        if (dp[i][c - 1] > previousMax) {
                            previousMax = dp[i][c - 1];
                            path[r][c] = i;
                        }
                    }
                } else {
                    // Move down
                    for (int i = r - 1; i >= 0; i--) {
                        if (dp[i][c - 1] > previousMax) {
                            previousMax = dp[i][c - 1];
                            path[r][c] = i;
                        }
                    }
                }
                dp[r][c] = previousMax + matrix[r][c];
            }
        }

        // Find the max sum and ending row
        int maxSum = 0;
        int lastRow = -1;
        for (int r = 0; r < rows; r++) {
            if (dp[r][cols - 1] > maxSum) {
                maxSum = dp[r][cols - 1];
                lastRow = r;
            }
        }

        // Reconstruct the path
        int[] resultPath = new int[cols];
        int col = cols - 1;
        while (col >= 0) {
            resultPath[col] = matrix[lastRow][col];
            lastRow = path[lastRow][col];
            col--;
        }

        // Output
        System.out.print(maxSum + " = ");
        for (int i = 0; i < cols; i++) {
            System.out.print(resultPath[i] + (i < cols - 1 ? " + " : ""));
        }
    }
}
