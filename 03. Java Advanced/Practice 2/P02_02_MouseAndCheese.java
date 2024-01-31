package JavaAdvanced.Practice2;

import java.util.Scanner;

public class P02_02_MouseAndCheese {
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
                if (matrix[row][col].equals("M")) {
                    currentRow = row;
                    currentCol = col;
                }
            }
        }

        int cheeseCount = 0;
        String command = scanner.nextLine();

        while (!command.equals("end")) {
            String direction = command;
            matrix[currentRow][currentCol] = "-";

            if (direction.equals("up")) {
                if (currentRow - 1 < 0) {
                    System.out.println("Where is the mouse?");
                    break;
                } else {
                    currentRow--;
                }
            } else if (direction.equals("down")) {
                if (currentRow + 1 >= size) {
                    System.out.println("Where is the mouse?");
                    break;
                } else {
                    currentRow++;
                }
            } else if (direction.equals("left")) {
                if (currentCol - 1 < 0) {
                    System.out.println("Where is the mouse?");
                    break;
                } else {
                    currentCol--;
                }
            } else if (direction.equals("right")) {
                if (currentCol + 1 >= size) {
                    System.out.println("Where is the mouse?");
                    break;
                } else {
                    currentCol++;
                }
            }

            String position = matrix[currentRow][currentCol];

            if (position.equals("-")) {
                matrix[currentRow][currentCol] = "M";
            } else if (position.equals("B")) {
                matrix[currentRow][currentCol] = "M";
                continue;
            } else if (position.equals("c")) {
                cheeseCount++;
                matrix[currentRow][currentCol] = "M";
            }

            command = scanner.nextLine();
        }

        if (cheeseCount < 5) {
            System.out.printf("The mouse couldn't eat the cheeses, she needed %d cheeses more.%n", 5 - cheeseCount);
        } else {
            System.out.printf("Great job, the mouse is fed %d cheeses!%n", cheeseCount);
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}
