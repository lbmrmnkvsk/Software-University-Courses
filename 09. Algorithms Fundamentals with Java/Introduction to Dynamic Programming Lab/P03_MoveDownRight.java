package L08_DP_Lab;

import java.util.*;

public class P03_MoveDownRight {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = Integer.parseInt(scanner.nextLine());
        int cols = Integer.parseInt(scanner.nextLine());
        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            matrix[i] = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
        }

        int[][] sums = new int[rows][cols];
        sums[0][0] = matrix[0][0];

        for (int i = 1; i < rows; i++) {
            sums[i][0] = sums[i - 1][0] + matrix[i][0];
        }

        for (int j = 1; j < cols; j++) {
            sums[0][j] = sums[0][j - 1] + matrix[0][j];
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                sums[i][j] = Math.max(sums[i - 1][j], sums[i][j - 1]) + matrix[i][j];
            }
        }

        int row = rows - 1;
        int col = cols - 1;
        String path = String.format("[%d, %d]", row, col);

        while (row > 0 || col > 0) {
            if (row == 0) {
                col--;
            } else if (col == 0) {
                row--;
            } else if (sums[row - 1][col] > sums[row][col - 1]) {
                row--;
            } else {
                col--;
            }

            path = String.format("[%d, %d] ", row, col) + path;
        }

        System.out.println(path);
    }
}
