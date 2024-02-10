package L07_GraphTheoryExercise;

import java.util.Scanner;

public class P07_TheMatrix {
    private static char[][] matrix;
    private static boolean[][] visited;
    private static char startChar;
    private static char fillChar;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tokens = scanner.nextLine().split("\\s+");
        int rows = Integer.parseInt(tokens[0]);
        int cols = Integer.parseInt(tokens[1]);
        matrix = new char[rows][cols];
        visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            matrix[i] = scanner.nextLine().replaceAll("\\s+", "").toCharArray();
        }

        fillChar = scanner.nextLine().charAt(0);
        tokens = scanner.nextLine().split("\\s+");
        int startRow = Integer.parseInt(tokens[0]);
        int startCol = Integer.parseInt(tokens[1]);
        startChar = matrix[startRow][startCol];

        fillMatrix(startRow, startCol);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    private static void fillMatrix(int row, int col) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length
        || matrix[row][col] != startChar || visited[row][col]) {
            return;
        }

        visited[row][col] = true;
        matrix[row][col] = fillChar;

        fillMatrix(row - 1, col);
        fillMatrix(row + 1, col);
        fillMatrix(row, col - 1);
        fillMatrix(row, col + 1);
    }
}
