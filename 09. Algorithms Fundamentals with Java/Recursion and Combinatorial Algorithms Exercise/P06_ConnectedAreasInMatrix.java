package L04_Recursion_and_Combinatorics_Exercise;

import java.util.Scanner;
import java.util.TreeSet;

class Area implements Comparable<Area> {
    int row;
    int col;
    int size;

    public Area(int row, int col, int size) {
        this.row = row;
        this.col = col;
        this.size = size;
    }

    @Override
    public int compareTo(Area other) {
        if (this.size != other.size) {
            return Integer.compare(other.size, this.size);
        }

        if (this.row != other.row) {
            return Integer.compare(this.row, other.row);
        }

        return Integer.compare(this.col, other.col);
    }
}

public class P06_ConnectedAreasInMatrix {
    private static char[][] matrix;
    private static boolean[][] visited;
    private static TreeSet<Area> areas;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = Integer.parseInt(scanner.nextLine());
        int cols = Integer.parseInt(scanner.nextLine());

        matrix = new char[rows][cols];
        visited = new boolean[rows][cols];
        areas = new TreeSet<>();

        for (int i = 0; i < rows; i++) {
            matrix[i] = scanner.nextLine().toCharArray();
        }

        findAreas();

        int number = 1;
        for (Area area : areas) {
            System.out.printf("Area #%d at (%d, %d), size: %d%n",
                    number, area.row, area.col, area.size);
            number++;
        }
    }

    private static void findAreas() {
        int count = 0;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (!visited[row][col] && matrix[row][col] == '-') {
                    int size = findAreaSize(row, col);
                    areas.add(new Area(row, col, size));
                    count++;
                }
            }
        }

        System.out.printf("Total areas found: %d%n", count);
    }

    private static int findAreaSize(int row, int col) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length
        || visited[row][col] || matrix[row][col] == '*') {
            return 0;
        }

        visited[row][col] = true;

        return 1 + findAreaSize(row - 1, col) + findAreaSize(row + 1, col) +
                findAreaSize(row, col - 1) + findAreaSize(row, col + 1);
    }
}
