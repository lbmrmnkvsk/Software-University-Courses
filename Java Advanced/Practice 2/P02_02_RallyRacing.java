package JavaAdvanced.Practice2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P02_02_RallyRacing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean hasFinished = false;
        int km = 0;
        int size = Integer.parseInt(scanner.nextLine());
        String carNumber = scanner.nextLine();
        String[][] matrix = new String[size][size];

        for (int row = 0; row < matrix.length; row++) {
            String[] arr = scanner.nextLine().split("\\s+");
            matrix[row] = arr;
        }

        int currentRow = 0;
        int currentCol = 0;
        List<Integer> tunnelCoordinates = new ArrayList<>();

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
            matrix[currentRow][currentCol] = ".";

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

            if (position.equals(".")) {
                km += 10;
                matrix[currentRow][currentCol] = "C";
            } else if (position.equals("T")) {
                km += 30;
                currentRow = tunnelCoordinates.get(2);
                currentCol = tunnelCoordinates.get(3);
                matrix[tunnelCoordinates.get(0)][tunnelCoordinates.get(1)] = ".";
                matrix[tunnelCoordinates.get(2)][tunnelCoordinates.get(3)] = ".";
                matrix[currentRow][currentCol] = "C";
            } else if (position.equals("F")) {
                km += 10;
                hasFinished = true;
                matrix[currentRow][currentCol] = "C";
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

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}
