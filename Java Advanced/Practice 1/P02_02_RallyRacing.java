package JavaAdvanced.z_Practice.MultimensionalArraysExercise;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class P02_02_RallyRacing {
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
        String direction = scanner.nextLine();
        boolean hasFinished = false;
        List<Integer> tunnelCoordinates = new ArrayList<>();
        findTunnelCoordinates(matrix, tunnelCoordinates);

        while (!direction.equals("End")) {
            if (direction.equals("left")) {
                currentCol--;
            } else if (direction.equals("right")) {
                currentCol++;
            } else if (direction.equals("up")) {
                currentRow--;
            } else if (direction.equals("down")) {
                currentRow++;
            }

            String currentPosition = matrix[currentRow][currentCol];

            if (currentPosition.equals(".")) {
                km += 10;
            } else if (currentPosition.equals("T")) {
                km += 30;
                currentRow = tunnelCoordinates.get(2);
                currentCol = tunnelCoordinates.get(3);
                matrix[tunnelCoordinates.get(0)][tunnelCoordinates.get(1)] = ".";
                matrix[tunnelCoordinates.get(2)][tunnelCoordinates.get(3)] = ".";
            } else if (currentPosition.equals("F")) {
                km += 10;
                hasFinished = true;
                break;
            }

            direction = scanner.nextLine();
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

    private static void findTunnelCoordinates(String[][] matrix, List<Integer> tunnelCoordinates) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                String currentElement = matrix[row][col];
                if (currentElement.equals("T")) {
                    tunnelCoordinates.add(row);
                    tunnelCoordinates.add(col);
                }
            }
        }
    }
}
