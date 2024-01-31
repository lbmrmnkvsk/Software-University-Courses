package L01_Recursion_and_Backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P05_FindAllPathsInALabyrinth {
    private static List<Character> path = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = Integer.parseInt(scanner.nextLine());
        int cols = Integer.parseInt(scanner.nextLine());

        char[][] labyrinth = new char[rows][cols];
        for (int i = 0; i < labyrinth.length; i++) {
            labyrinth[i] = scanner.nextLine().toCharArray();
        }

        findPaths(labyrinth, 0, 0 , 'S');
    }

    private static void findPaths(char[][] labyrinth, int row, int col, char direction) {
        if (!isInBounds(labyrinth, row, col) || labyrinth[row][col] == '*' || labyrinth[row][col] == 'v') {
            return;
        }

        path.add(direction);

        if (labyrinth[row][col] == 'e') {
            printPath();

        } else {
            labyrinth[row][col] = 'v'; // Mark the cell as visited

            findPaths(labyrinth, row - 1, col, 'U'); // Up
            findPaths(labyrinth, row + 1, col, 'D'); // Down
            findPaths(labyrinth, row, col - 1, 'L'); // Left
            findPaths(labyrinth, row, col + 1, 'R'); // Right

            labyrinth[row][col] = '-'; // Unmark the cell
        }

        path.remove(path.size() - 1);
    }

    private static boolean isInBounds(char[][] labyrinth, int row, int col) {
        return row >= 0 && row < labyrinth.length && col >= 0 && col < labyrinth[row].length;
    }

    private static void printPath() {
        for (int i = 1; i < path.size(); i++) {
            System.out.print(path.get(i));
        }
    }
}
