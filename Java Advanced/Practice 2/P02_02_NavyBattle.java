package JavaAdvanced.Practice2;

import java.util.Scanner;

public class P02_02_NavyBattle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        String[][] matrix = new String[size][size];

        for (int row = 0; row < matrix.length; row++) {
            String[] arr = scanner.nextLine().split("");
            matrix[row] = arr;
        }

        int currentRow = -1;
        int currentCol = -1;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("S")) {
                    currentRow = row;
                    currentCol = col;
                }
            }
        }

        int countMines = 0;
        int countCruisers = 0;

        while (true) {
            String direction = scanner.nextLine();
            matrix[currentRow][currentCol] = "-";

            if (direction.equals("up")) {
                currentRow--;
            } else if (direction.equals("down")) {
                currentRow++;
            } else if (direction.equals("left")) {
                currentCol--;
            } else if (direction.equals("right")) {
                currentCol++;
            }

            String position = matrix[currentRow][currentCol];

            if (position.equals("-")) {
                matrix[currentRow][currentCol] = "S";
            } else if (position.equals("*")) {
                countMines++;
                matrix[currentRow][currentCol] = "S";
                if (countMines >= 3) {
                    System.out.printf("Mission failed, U-9 disappeared! Last known coordinates [%d, %d]!%n", currentRow, currentCol);
                    break;
                }
            } else if (position.equals("C")) {
                countCruisers++;
                matrix[currentRow][currentCol] = "S";
                if (countCruisers >= 3) {
                    System.out.println("Mission accomplished, U-9 has destroyed all battle cruisers of the enemy!");
                    break;
                }
            }
        }

        for (int row = 0; row < matrix.length; row++) {
            for ( int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}
