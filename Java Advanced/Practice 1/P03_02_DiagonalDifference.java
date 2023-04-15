package JavaAdvanced.z_Practice.MultimensionalArraysExercise;
import java.util.Arrays;
import java.util.Scanner;
public class P03_02_DiagonalDifference {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        int[][] matrix = new int[size][size];
        fillMatrix(matrix, scanner);
        int sumPrimaryDiagonal = getSumPrimaryDiagonal(matrix);
        int sumSecondaryDiagonal = getSumSecondaryDiagonal(matrix);
        System.out.print(Math.abs(sumPrimaryDiagonal - sumSecondaryDiagonal));
    }

    private static void fillMatrix(int[][] matrix, Scanner scanner) {
        for (int row = 0; row < matrix.length; row++) {
            int[] arr = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            matrix[row] = arr;
        }
    }

    private static int getSumPrimaryDiagonal(int[][] matrix) {
        int sum = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                int currentElement = matrix[row][col];
                if (row == col) {
                    sum += currentElement;
                }
            }
        }
        return sum;
    }

    private static int getSumSecondaryDiagonal(int[][] matrix) {
        int sum = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                int currentElement = matrix[row][col];
                if (col == (matrix.length - row - 1) ) {
                    sum += currentElement;
                }
            }
        }
        return sum;
    }
}
