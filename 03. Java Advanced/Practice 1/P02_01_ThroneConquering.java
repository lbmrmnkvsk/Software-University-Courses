package JavaAdvanced.z_Practice.MultimensionalArraysExercise;

import java.util.Scanner;

public class P02_01_ThroneConquering {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int energy = Integer.parseInt(scanner.nextLine());
        int rows = Integer.parseInt(scanner.nextLine());
        String[][] matrix = new String[rows][];

        for (int row = 0; row < matrix.length; row++) {
            String[] arr = scanner.nextLine().split("");
            matrix[row] = arr;
        }

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

        int cols = matrix[0].length;
        String command = scanner.nextLine();
        boolean hasDied = false;

        while (true) {
            String[] tokens = command.split("\\s+");
            String direction = tokens[0];
            int spartanRow = Integer.parseInt(tokens[1]);
            int spartanCol = Integer.parseInt(tokens[2]);
            matrix[spartanRow][spartanCol] = "S";

            matrix[currentRow][currentCol] = "-";


            if (direction.equals("up")) {
                if (currentRow - 1 >= 0) {
                    currentRow--;
                }
            } else if (direction.equals("down")) {
                if (currentRow + 1 < rows) {
                    currentRow++;
                }
            } else if (direction.equals("left")) {
                if (currentCol - 1 >= 0) {
                    currentCol--;
                }
            } else if (direction.equals("right")) {
                if (currentCol + 1 < cols) {
                    currentCol++;
                }
            }

            energy--;
            if (energy <= 0) {
                hasDied = true;
                matrix[currentRow][currentCol] = "X";
                break;
            }

            String position = matrix[currentRow][currentCol];

            if (position.equals("-")) {
                matrix[currentRow][currentCol] = "P";
            } else if (position.equals("S")) {
                energy -= 2;
                if (energy <= 0) {
                    hasDied = true;
                    matrix[currentRow][currentCol] = "X";
                    break;
                } else {
                    matrix[currentRow][currentCol] = "P";
                }
            } else if (position.equals("H")) {
                matrix[currentRow][currentCol] = "-";
                break;
            }

            command = scanner.nextLine();
        }

        if (hasDied) {
            System.out.printf("Paris died at %d;%d.%n", currentRow, currentCol);
        } else {
            System.out.printf("Paris has successfully abducted Helen! Energy left: %d%n", energy);
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}
