package JavaAdvanced.z_Practice.MultidimensionalArraysLab;
import java.util.Arrays;
import java.util.Scanner;
public class P01_02_CompareMatrices {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions1 = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int firstMatrixRows = dimensions1[0];
        int firstMatrixCols = dimensions1[1];
        int[][] firstMatrix = new int[firstMatrixRows][firstMatrixCols];

        for (int row = 0; row < firstMatrix.length; row++) {
            int[] arr = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            firstMatrix[row] = arr;
        }

        int[] dimensions2 = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int secondMatrixRows = dimensions2[0];
        int secondMatrixCols = dimensions2[1];
        int[][] secondMatrix = new int[secondMatrixRows][secondMatrixCols];

        for (int row = 0; row < secondMatrix.length; row++) {
            int[] arr = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            secondMatrix[row] = arr;
        }

        if (matricesAreEqual(firstMatrix, secondMatrix)) {
            System.out.print("equal");
        } else {
            System.out.print("not equal");
        }
    }

    private static boolean matricesAreEqual(int[][] firstMatrix, int[][] secondMatrix) {
        if (firstMatrix.length != secondMatrix.length) {
            return false;
        }

        for (int row = 0; row < firstMatrix.length; row++) {
            if (firstMatrix[row].length != secondMatrix[row].length) {
                return false;
            }
            for (int col = 0; col < firstMatrix[row].length; col++) {
                if (firstMatrix[row][col] != secondMatrix[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }
}
