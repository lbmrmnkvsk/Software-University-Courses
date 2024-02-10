package L07_GraphTheoryExercise;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class P02_AreasInMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[rows][];
        boolean[][] visited = new boolean[rows][];
        for (int i = 0; i < rows; i++) {
            matrix[i] = scanner.nextLine().toCharArray();
            boolean[] booleans = new boolean[matrix[i].length];
            Arrays.fill(booleans, false);
            visited[i] = booleans;
        }

        Map<Character, Integer> lettersToCount = new TreeMap<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (!visited[i][j]) {
                    char letter = matrix[i][j];
                    lettersToCount.putIfAbsent(letter, 0);
                    lettersToCount.put(letter, lettersToCount.get(letter) + 1);
                    traverse(matrix, visited, i, j, letter);
                }
            }
        }

        int count = lettersToCount.values().stream().mapToInt(Integer::intValue).sum();
        System.out.printf("Areas: %d%n", count);
        for (Map.Entry<Character, Integer> entry : lettersToCount.entrySet()) {
            System.out.printf("Letter '%c' -> %d%n", entry.getKey(), entry.getValue());
        }
    }

    private static void traverse(char[][] matrix, boolean[][] visited, int row, int col, char letter) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length
        || matrix[row][col] != letter || visited[row][col]) {
            return;
        }

        visited[row][col] = true;

        traverse(matrix, visited, row + 1, col, letter);
        traverse(matrix, visited, row - 1, col, letter);
        traverse(matrix, visited, row, col + 1, letter);
        traverse(matrix, visited, row, col - 1, letter);
    }
}
