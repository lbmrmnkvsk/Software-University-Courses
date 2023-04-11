package JavaAdvanced.z_Practice.MultidimensionalArraysLab;
import java.util.Arrays;
import java.util.Scanner;
public class P02_02_PositionsOf {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int rows = dimensions[0];
        int cols = dimensions[1];
        int[][] matrix = new int[rows][cols];

        for (int row = 0; row < matrix.length; row++) {
            int[] arr = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            matrix[row] = arr;
        }

        int searchNumber = Integer.parseInt(scanner.nextLine());
        boolean isFound = false;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == searchNumber) {
                    isFound = true;
                    System.out.println(row + " " + col);
                }
            }
        }
        if (!isFound) {
            System.out.print("not found");
        }
    }
}
