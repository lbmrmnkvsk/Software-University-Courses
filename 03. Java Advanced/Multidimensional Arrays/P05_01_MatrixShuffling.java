package JavaAdvanced.z_Practice.MultimensionalArraysExercise;
import java.util.Arrays;
import java.util.Scanner;
public class P05_01_MatrixShuffling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int rows = dimensions[0];
        int cols = dimensions[1];
        String[][] matrix = new String[rows][cols];

        for (int row = 0; row < matrix.length; row++) {
            String[] arr = scanner.nextLine().split("\\s+");
            matrix[row] = arr;
        }
        String command = scanner.nextLine();

        while (!command.equals("END")) {
            if (isValidCommand(matrix, command)) {
                String[] commandParts = command.split("\\s+");
                int row1 = Integer.parseInt(commandParts[1]);
                int col1 = Integer.parseInt(commandParts[2]);
                int row2 = Integer.parseInt(commandParts[3]);
                int col2 = Integer.parseInt(commandParts[4]);

                String firstElement = matrix[row1][col1];
                String secondElement = matrix[row2][col2];
                matrix[row1][col1] = secondElement;
                matrix[row2][col2] = firstElement;

                printMatrix(matrix);
            } else {
                System.out.println("Invalid input!");
            }

            command = scanner.nextLine();
        }
    }

    private static boolean isValidCommand(String[][] matrix, String command) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        String[] commandParts = command.split("\\s+");

        if (commandParts.length != 5) {
            return false;
        }

        if (!commandParts[0].equals("swap")) {
            return false;
        }

        int row1 = Integer.parseInt(commandParts[1]);
        int col1 = Integer.parseInt(commandParts[2]);
        int row2 = Integer.parseInt(commandParts[3]);
        int col2 = Integer.parseInt(commandParts[4]);

        if (row1 < 0 || row1 >= rows || col1 < 0 || col1 >= cols ||
            row2 < 0 || row2 >= rows || col2 < 0 || col2 >= cols) {
            return false;
        }

        return true;
    }

    private static void printMatrix(String[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }
}
