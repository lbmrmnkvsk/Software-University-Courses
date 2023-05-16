package JavaAdvanced.z_Practice.MultimensionalArraysExercise;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class P02_01_RallyRacing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        String carNumber = scanner.nextLine();
        int km = 0;
        String[][] trace = new String[n][n];

        for (int row = 0; row < trace.length; row++) {
            String[] arr = scanner.nextLine().split("\\s+");
            trace[row] = arr;
        }

        int currentRow = 0;
        int currentCol = 0;
        List<Integer> tunnelsCoordinates = new ArrayList<>();
        findTunnels(trace, tunnelsCoordinates);
        boolean hasFinished = false;

        String direction = scanner.nextLine();
        while (!direction.equals("End")) {
            switch (direction) {
                case "left":
                    currentCol--;
                    break;
                case "right":
                    currentCol++;
                    break;
                case "up":
                    currentRow--;
                    break;
                case "down":
                    currentRow++;
                    break;
            }

            String movedPlace = trace[currentRow][currentCol];

            if (movedPlace.equals(".")) {
                km += 10;
            } else if (movedPlace.equals("F")) {
                System.out.printf("Racing car %s finished the stage!%n", carNumber);
                km += 10;
                hasFinished = true;
                break;
            } else if (movedPlace.equals("T")) {
                currentRow = tunnelsCoordinates.get(2);
                currentCol = tunnelsCoordinates.get(3);
                km += 30;
                trace[tunnelsCoordinates.get(0)][tunnelsCoordinates.get(1)] = ".";
                trace[tunnelsCoordinates.get(2)][tunnelsCoordinates.get(3)] = ".";
            }

            direction = scanner.nextLine();
        }

        if (!hasFinished) {
            System.out.printf("Racing car %s DNF.%n", carNumber);
        }

        System.out.printf("Distance covered %d km.%n", km);
        trace[currentRow][currentCol] = "C";

        for (int row = 0; row < trace.length; row++) {
            for (int col = 0; col < trace[row].length; col++) {
                System.out.print(trace[row][col]);
            }
            System.out.println();
        }
    }

    private static void findTunnels(String[][] trace, List<Integer> tunnelsCoordinates) {
        for (int row = 0; row < trace.length; row++) {
            for (int col = 0; col < trace[row].length; col++) {
                String currentElement = trace[row][col];
                if (currentElement.equals("T")) {
                    tunnelsCoordinates.add(row);
                    tunnelsCoordinates.add(col);
                }
            }
        }
    }

}
