package JavaAdvanced.z_Practice.MultimensionalArraysExercise;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class P02_03_RallyRacing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        String carNumber = scanner.nextLine();
        String[][] matrix = new String[n][n];

        for (int row = 0; row < matrix.length; row++) {
            String[] arr = scanner.nextLine().split("\\s+");
            matrix[row] = arr;
        }

        int currentRow = 0;
        int currentCol = 0;
        int km = 0;
        List<Integer> tunnelCoordinates = new ArrayList<>();
        boolean hasFinished = false;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("T")) {
                    tunnelCoordinates.add(row);
                    tunnelCoordinates.add(col);
                }
            }
        }

        String command = scanner.nextLine();

        while (!command.equals("End")) {
            String direction = command;
            if (direction.equals("left")) {
                currentCol--;
            } else if (direction.equals("right")) {
                currentCol++;
            } else if (direction.equals("up")) {
                currentRow--;
            } else if (direction.equals("down")) {
                currentRow++;
            }

            String position = matrix[currentRow][currentCol];

            if (position.equals(".")) {
                km += 10;
            } else if (position.equals("T")) {
                currentRow = tunnelCoordinates.get(2);
                currentCol = tunnelCoordinates.get(3);
                km += 30;
                matrix[tunnelCoordinates.get(0)][tunnelCoordinates.get(1)] = ".";
                matrix[tunnelCoordinates.get(2)][tunnelCoordinates.get(3)] = ".";
            } else if (position.equals("F")) {
                hasFinished = true;
                km += 10;
                break;
            }

            command = scanner.nextLine();
        }

        if (hasFinished) {
            System.out.printf("Racing car %s finished the stage!%n", carNumber);
        } else {
            System.out.printf("Racing car %s DNF.%n", carNumber);
        }

        System.out.printf("Distance covered %d km.%n", km);
        matrix[currentRow][currentCol] = "C";

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}
