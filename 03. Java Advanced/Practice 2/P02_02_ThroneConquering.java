package JavaAdvanced.Practice2;

import java.util.Scanner;

public class P02_02_ThroneConquering {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int energy = Integer.parseInt(scanner.nextLine());
        int rows = Integer.parseInt(scanner.nextLine());
        String[][] matrix = new String[rows][];

        for (int row = 0; row < matrix.length; row++) {
            String[] arr = scanner.nextLine().split("");
            matrix[row] = arr;
        }

        int cols = matrix[0].length;
        int currentRow = -1;
        int currentCol = -1;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("P")) {
                    currentRow = row;
                    currentCol = col;
                }
            }
        }

        while (true) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String direction = tokens[0];
            int spartanRow = Integer.parseInt(tokens[1]);
            int spartanCol = Integer.parseInt(tokens[2]);
            matrix[spartanRow][spartanCol] = "S";
            matrix[currentRow][currentCol] = "-";

            if (direction.equals("up")) {
                if (currentRow - 1 < 0) {
                    //nothing
                } else {
                    currentRow--;
                }
            } else if (direction.equals("down")) {
                if (currentRow + 1 >= rows) {
                    //nothing
                } else {
                    currentRow++;
                }
            } else if (direction.equals("left")) {
                if (currentCol - 1 < 0) {
                    //nothing
                } else {
                    currentCol--;
                }
            } else if (direction.equals("right")) {
                if (currentCol + 1 >= cols) {
                    //nothing
                } else {
                    currentCol++;
                }
            }

            energy--;
            String position = matrix[currentRow][currentCol];

            if (position.equals("-")) {
                matrix[currentRow][currentCol] = "P";
                if (energy <= 0) {
                    matrix[currentRow][currentCol] = "X";
                    System.out.printf("Paris died at %d;%d.%n", currentRow, currentCol);
                    break;
                }
            } else if (position.equals("S")) {
                energy -= 2;
                matrix[currentRow][currentCol] = "P";
                if (energy <= 0) {
                    matrix[currentRow][currentCol] = "X";
                    System.out.printf("Paris died at %d;%d.%n", currentRow, currentCol);
                    break;
                }
            } else if (position.equals("H")) {
                matrix[currentRow][currentCol] = "-";
                System.out.printf("Paris has successfully abducted Helen! Energy left: %d%n", energy);
                break;
            }
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}
