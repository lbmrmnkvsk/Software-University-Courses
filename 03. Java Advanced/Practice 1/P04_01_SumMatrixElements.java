package JavaAdvanced.z_Practice.MultidimensionalArraysLab;
import java.util.Arrays;
import java.util.Scanner;
public class P04_01_SumMatrixElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        int rows = dimensions[0];
        int columns = dimensions[1];
        int[][] matrix = new int[rows][columns];

        for (int row = 0; row < rows; row++) {
            int[] arr = Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();
            matrix[row] = arr;
        }

        int sum = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                sum += matrix[row][column];
            }
        }

        System.out.println(rows);
        System.out.println(columns);
        System.out.print(sum);
    }
}
