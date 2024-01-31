package JavaAdvanced.Practice2;

import java.util.Scanner;

public class P02_01_Bee {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        String[][] matrix = new String[size][size];

        for (int row = 0; row < matrix.length; row++) {
            String[] arr = scanner.nextLine().split("");
            matrix[row] = arr;
        }

        int flowers = 0;
        int currentRow = -1;
        int currentCol = -1;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("B")) {
                    currentRow = row;
                    currentCol = col;
                }
            }
        }

        String command = scanner.nextLine();

        while (!command.equals("End")) {
            String direction = command;
            matrix[currentRow][currentCol] = ".";

            if (direction.equals("up")) {
                if (currentRow - 1 < 0) {
                    System.out.println("The bee got lost!");
                    break;
                } else {
                    currentRow--;
                }
            } else if (direction.equals("down")) {
                if (currentRow + 1 >= size) {
                    System.out.println("The bee got lost!");
                    break;
                } else {
                    currentRow++;
                }
            } else if (direction.equals("left")) {
                if (currentCol - 1 < 0) {
                    System.out.println("The bee got lost!");
                    break;
                } else {
                    currentCol--;
                }
            } else if (direction.equals("right")) {
                if (currentCol + 1 >= size) {
                    System.out.println("The bee got lost!");
                    break;
                } else {
                    currentCol++;
                }
            }

            String position = matrix[currentRow][currentCol];

            if (position.equals(".")) {
                matrix[currentRow][currentCol] = "B";
            } else if (position.equals("f")) {
                flowers++;
                matrix[currentRow][currentCol] = "B";
            } else if (position.equals("O")) {
                matrix[currentRow][currentCol] = "B";
                continue;
            }

            command = scanner.nextLine();
        }

        if (flowers < 5) {
            System.out.printf("The bee couldn't pollinate the flowers, she needed %d flowers more%n", 5 - flowers);
        } else {
            System.out.printf("Great job, the bee manage to pollinate %d flowers!%n", flowers);
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}
