package JavaAdvanced.z_Practice.MultidimensionalArraysLab;
import java.util.Arrays;
import java.util.Scanner;
public class P05_02_MaximumSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        int rows = dimensions[0];
        int cols = dimensions[1];
        int[][] matrix = new int [rows][cols];

        for (int row = 0; row < matrix.length; row++) {
            int[] arr = Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();
            matrix[row] = arr;
        }

        int maxSum = Integer.MIN_VALUE;
        int topLeftRow = -1;
        int topLeftCol = -1;

        for (int row = 0; row < matrix.length - 1; row++) {
            for (int col = 0; col < matrix[row].length - 1; col++) {
                int currentSum = matrix[row][col] + matrix[row][col + 1] +
                                 matrix[row +1][col] + matrix[row + 1][col + 1];
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    topLeftRow = row;
                    topLeftCol = col;
                }
            }
        }

        System.out.println(matrix[topLeftRow][topLeftCol] + " " + matrix[topLeftRow][topLeftCol + 1]);
        System.out.println(matrix[topLeftRow + 1][topLeftCol] + " " + matrix[topLeftRow + 1][topLeftCol +1]);
        System.out.print(maxSum);
    }
}
