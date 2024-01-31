package JavaAdvanced.Exam;

import java.util.Arrays;
import java.util.Scanner;

public class P02_01_MouseInTheKitchen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int rows = dimensions[0];
        int cols = dimensions[1];
        String[][] matrix = new String[rows][cols];

        for (int row = 0; row < matrix.length; row++) {
            String[] arr = scanner.nextLine().split("");
            matrix[row] = arr;
        }

        int cheeseCount = 0;
        int currentRow = -1;
        int currentCol = -1;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("C")) {
                    cheeseCount++;
                }
                if (matrix[row][col].equals("M")) {
                    currentRow = row;
                    currentCol = col;
                }
            }
        }

        String command = scanner.nextLine();
        int eatenCheese = 0;
        boolean hasEatenAllCheese = false;
        boolean goesOut = false;
        boolean isTrapped = false;

        while (!command.equals("danger")) {
            String direction = command;
            matrix[currentRow][currentCol] = "*";

            if (direction.equals("up")) {
                if (currentRow -1 < 0) {
                    matrix[currentRow][currentCol] = "M";
                    System.out.println("No more cheese for tonight!");
                    goesOut = true;
                    break;
                } else if (matrix[currentRow - 1][currentCol].equals("@")) {
                    //nothing
                } else {
                    currentRow--;
                }
            } else if (direction.equals("down")) {
                if (currentRow + 1 >= rows) {
                    matrix[currentRow][currentCol] = "M";
                    System.out.println("No more cheese for tonight!");
                    goesOut = true;
                    break;
                } else if (matrix[currentRow + 1][currentCol].equals("@")) {
                    //nothing
                } else {
                    currentRow++;
                }
            } else if (direction.equals("left")) {
                if (currentCol - 1 < 0) {
                    matrix[currentRow][currentCol] = "M";
                    System.out.println("No more cheese for tonight!");
                    goesOut = true;
                    break;
                } else if (matrix[currentRow][currentCol - 1].equals("@")) {
                    //nothing
                } else {
                    currentCol--;
                }
            } else if (direction.equals("right")) {
                if (currentCol + 1 >= cols) {
                    matrix[currentRow][currentCol] = "M";
                    System.out.println("No more cheese for tonight!");
                    goesOut = true;
                    break;
                } else if (matrix[currentRow][currentCol + 1].equals("@")) {
                    //nothing
                } else {
                    currentCol++;
                }
            }

            String position = matrix[currentRow][currentCol];

            if (position.equals("*")) {
                matrix[currentRow][currentCol] = "M";
            } else if (position.equals("C")) {
                eatenCheese++;
                matrix[currentRow][currentCol] = "M";
                if (eatenCheese >= cheeseCount) {
                    hasEatenAllCheese = true;
                    System.out.println("Happy mouse! All the cheese is eaten, good night!");
                    break;
                }
            } else if (position.equals("T")) {
                matrix[currentRow][currentCol] = "M";
                System.out.println("Mouse is trapped!");
                isTrapped = true;
                break;
            }

            command = scanner.nextLine();
        }

        if (!hasEatenAllCheese && !isTrapped && !goesOut) {
            System.out.println("Mouse will come back later!");
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}
