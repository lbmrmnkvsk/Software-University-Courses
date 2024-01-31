package JavaAdvanced.z_Practice.MultimensionalArraysExercise;
import java.util.Scanner;
public class P01_02_FillTheMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputParts = scanner.nextLine().split(", ");
        int size = Integer.parseInt(inputParts[0]);
        String pattern = inputParts[1];
        int[][] matrix = new int[size][size];

        if (pattern.equals("A")) {
            fillMatrixPatternA(matrix);
        } else if (pattern.equals("B")) {
            fillMatrixPatternB(matrix);
        }

        printMatrix(matrix);
    }

    private static void fillMatrixPatternA(int[][] matrix) {
        int startNumber = 1;
        for (int col = 0; col < matrix.length; col++) {
            for (int row = 0; row < matrix.length; row++) {
                matrix[row][col] = startNumber;
                startNumber++;
            }
        }
    }

    private static void fillMatrixPatternB(int[][] matrix) {
        int startNumber = 1;
        for (int col = 0; col < matrix.length; col++) {
            if ((col + 1) % 2 == 1) {
                for (int row = 0; row < matrix.length; row++) {
                    matrix[row][col] = startNumber;
                    startNumber++;
                }
            } else {
                for (int row = matrix.length - 1; row >= 0; row--) {
                    matrix[row][col] = startNumber;
                    startNumber++;
                }
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }
}
