package JavaAdvanced.z_Practice.MultidimensionalArraysLab;
import java.util.Arrays;
import java.util.Scanner;
public class P02_01_PositionsOf {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int rows = dimensions[0];
        int columns = dimensions[1];

        int[][] matrix = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            int[] arr = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            matrix[i] = arr;
        }

        int searchNumber = Integer.parseInt(scanner.nextLine());
        boolean isFound = false;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == searchNumber) {
                    isFound = true;
                    System.out.println(i + " " + j);
                }
            }
        }

        if (!isFound) {
            System.out.println("not found");
        }
    }
}
