package JavaAdvanced.z_Practice.MultimensionalArraysExercise;
import java.util.Scanner;
public class P02_01_MouseAndCheese {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        String[][] matrix = new String[n][n];

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

        int countCheese = 0;
        String command = scanner.nextLine();

        while (!command.equals("end")) {
            String direction = command;
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

            if (currentRow < 0 || currentRow >= n || currentCol < 0 || currentCol >= n) {
                System.out.println("Where is the mouse?");
                break;
            }

            String mousePosition = matrix[currentRow][currentCol];

            if (mousePosition.equals("-")) {
                matrix[currentRow][currentCol] = "M";
            } else if (mousePosition.equals("c")) {
                countCheese++;
                matrix[currentRow][currentCol] = "M";
            } else if (mousePosition.equals("B")) {
                continue;
            }

            command = scanner.nextLine();
        }

        if (countCheese < 5) {
            System.out.printf("The mouse couldn't eat the cheeses, she needed %d cheeses more.%n", 5 - countCheese);
        } else {
            System.out.printf("Great job, the mouse is fed %d cheeses!%n", countCheese);
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}
