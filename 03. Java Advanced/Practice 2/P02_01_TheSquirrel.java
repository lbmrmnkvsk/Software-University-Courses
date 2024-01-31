package JavaAdvanced.Practice2;

import java.util.Scanner;

public class P02_01_TheSquirrel {
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

        int countHazelnuts = 0;
        boolean steppedOnTrap = false;
        boolean wentOut = false;

        for (String direction : directions) {
            matrix[currentRow][currentCol] = "*";
            if (direction.equals("up")) {
                if (currentRow - 1 < 0) {
                    wentOut = true;
                    break;
                } else {
                    currentRow--;
                }
            } else if (direction.equals("down")) {
                if (currentRow + 1 >= size) {
                    wentOut = true;
                    break;
                } else {
                    currentRow++;
                }
            } else if (direction.equals("left")) {
                if (currentCol - 1 < 0) {
                    wentOut = true;
                    break;
                } else {
                    currentCol--;
                }
            } else if (direction.equals("right")) {
                if (currentCol + 1 >= size) {
                    wentOut = true;
                    break;
                } else {
                    currentCol++;
                }
            }

            String position = matrix[currentRow][currentCol];

            if (position.equals("h")) {
                countHazelnuts++;
                matrix[currentRow][currentCol] = "s";
                if (countHazelnuts >= 3) {
                    break;
                }
            } else if (position.equals("*")) {
                matrix[currentRow][currentCol] = "s";
            } else if (position.equals("t")) {
                steppedOnTrap = true;
                matrix[currentRow][currentCol] = "*";
                break;
            }
        }

        if (wentOut) {
            System.out.println("The squirrel is out of the field.");
        }

        if (steppedOnTrap) {
            System.out.println("Unfortunately, the squirrel stepped on a trap...");
        }

        if (!wentOut && !steppedOnTrap) {
            if (countHazelnuts < 3) {
                System.out.println("There are more hazelnuts to collect.");
            } else {
                System.out.println("Good job! You have collected all hazelnuts!");
            }
        }

        System.out.printf("Hazelnuts collected: %d", countHazelnuts);
    }
}
