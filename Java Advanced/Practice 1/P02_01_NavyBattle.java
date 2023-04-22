package JavaAdvanced.z_Practice.MultimensionalArraysExercise;
import java.util.Arrays;
import java.util.Scanner;
public class P02_01_NavyBattle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        String[][] matrix = new String[size][size];

        for (int row = 0; row < matrix.length; row++) {
            String[] arr = scanner.nextLine().split("");
            matrix[row] = arr;
        }

        int submarineRow = -1;
        int submarineCol = -1;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("S")) {
                    submarineRow = row;
                    submarineCol = col;
                }
            }
        }

        int countMines = 0;
        int countCruisers = 0;
        String direction = scanner.nextLine();

        while (true) {
            matrix[submarineRow][submarineCol] = "-";

            if (direction.equals("up")) {
                submarineRow--;
            } else if (direction.equals("down")) {
                submarineRow++;
            } else if (direction.equals("left")) {
                submarineCol--;
            } else if (direction.equals("right")) {
                submarineCol++;
            }

            String submarinePosition = matrix[submarineRow][submarineCol];
            if (submarinePosition.equals("-")) {
                matrix[submarineRow][submarineCol] = "S";
            } else if (submarinePosition.equals("*")) {
                countMines++;
                matrix[submarineRow][submarineCol] = "S";
                if (countMines == 3) {
                    System.out.printf("Mission failed, U-9 disappeared! Last known coordinates [%d, %d}]!%n", submarineRow, submarineCol);
                    break;
                }
            } else if (submarinePosition.equals("C")) {
                countCruisers++;
                matrix[submarineRow][submarineRow] = "S";
                if (countCruisers == 3) {
                    System.out.println("Mission accomplished, U-9 has destroyed all battle cruisers of the enemy!");
                    break;
                }
            }
        }
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}
