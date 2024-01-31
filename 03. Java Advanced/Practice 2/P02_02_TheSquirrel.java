package JavaAdvanced.Practice2;

import java.util.Scanner;

public class P02_02_TheSquirrel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        String[] directions = scanner.nextLine().split(", ");
        String[][] matrix = new String[size][size];

        for (int row = 0; row < matrix.length; row++) {
            String[] arr = scanner.nextLine().split("");
            matrix[row] = arr;
        }

        int currentRow = -1;
        int currentCol = -1;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("s")) {
                    currentRow = row;
                    currentCol = col;
                }
            }
        }

        int hazelnuts = 0;
        boolean goesOut = false;
        boolean steppedOnTrap = false;

        for (String direction : directions) {
            matrix[currentRow][currentCol] = "*";

            if (direction.equals("up")) {
                if (currentRow - 1 < 0) {
                    goesOut = true;
                    break;
                } else {
                    currentRow--;
                }
            } else if (direction.equals("down")) {
                if (currentRow + 1 >= size) {
                    goesOut = true;
                    break;
                } else {
                    currentRow++;
                }
            } else if (direction.equals("left")) {
                if (currentCol - 1 < 0) {
                    goesOut = true;
                    break;
                } else {
                    currentCol--;
                }
            } else if (direction.equals("right")) {
                if (currentCol + 1 >= size) {
                    goesOut = true;
                    break;
                } else {
                    currentCol++;
                }
            }

            String position = matrix[currentRow][currentCol];

            if (position.equals("*")) {
                matrix[currentRow][currentCol] = "s";
            } else if (position.equals("h")) {
                hazelnuts++;
                matrix[currentRow][currentCol] = "s";
            } else if (position.equals("t")) {
                steppedOnTrap = true;
                matrix[currentRow][currentCol] = "*";
            }
        }

        if (goesOut) {
            System.out.println("The squirrel is out of the field.");
        } else if (steppedOnTrap) {
            System.out.println("Unfortunately, the squirrel stepped on a trap...");
        } else if (hazelnuts < 3) {
            System.out.println("There are more hazelnuts to collect.");
        } else if (hazelnuts >= 3) {
            System.out.println("Good job! You have collected all hazelnuts!");
        }

        System.out.printf("Hazelnuts collected: %d", hazelnuts);
    }
}
