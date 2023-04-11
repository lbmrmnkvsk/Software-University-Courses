package JavaAdvanced.z_Practice.MultidimensionalArraysLab;
import java.util.Arrays;
import java.util.Scanner;
public class P06_01_PrintDiagonals {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        int[][] matrix = new int[size][size];

        for (int row = 0; row < matrix.length; row ++) {
            int[] arr = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            matrix[row] = arr;
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (row == col) {
                    System.out.print(matrix[row][col] + " ");
                }
            }
        }
        System.out.println();

        for (int row = size - 1, col = 0; col < size; row--, col++) {
            System.out.print(matrix[row][col] + " ");
        }
    }
}
