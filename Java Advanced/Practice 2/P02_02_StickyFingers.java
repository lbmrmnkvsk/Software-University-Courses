package JavaAdvanced.Practice2;

import java.util.Scanner;

public class P02_02_StickyFingers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        String[][] matrix = new String[size][size];
        String[] directions = scanner.nextLine().split(",");

        for (int row = 0; row < matrix.length; row++) {
            String[] arr = scanner.nextLine().split("\\s+");
            matrix[row] = arr;
        }

        int currentRow = -1;
        int currentCol = -1;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("D")) {
                    currentRow = row;
                    currentCol = col;
                }
            }
        }

        boolean wasCaught = false;
        int totalMoney = 0;

        for (String direction : directions) {
            matrix[currentRow][currentCol] = "+";

            if (direction.equals("up")) {
                if (currentRow - 1 < 0) {
                    System.out.println("You cannot leave the town, there is police outside!");
                } else {
                    currentRow--;
                }
            } else if (direction.equals("down")) {
                if (currentRow + 1 >= size) {
                    System.out.println("You cannot leave the town, there is police outside!");
                } else {
                    currentRow++;
                }
            } else if (direction.equals("left")) {
                if (currentCol - 1 < 0) {
                    System.out.println("You cannot leave the town, there is police outside!");
                } else {
                    currentCol--;
                }
            } else if (direction.equals("right")) {
                if (currentCol + 1 >= size) {
                    System.out.println("You cannot leave the town, there is police outside!");
                } else {
                    currentCol++;
                }
            }

            String position = matrix[currentRow][currentCol];

            if (position.equals("+")) {
                matrix[currentRow][currentCol] = "D";
            } else if (position.equals("$")) {
                int currentMoney = currentRow * currentCol;
                totalMoney += currentMoney;
                System.out.printf("You successfully stole %d$.%n", currentMoney);
                matrix[currentRow][currentCol] = "D";
            } else if (position.equals("P")) {
                wasCaught = true;
                matrix[currentRow][currentCol] = "#";
                break;
            }
        }

        if (!wasCaught) {
            System.out.printf("Your last theft has finished successfully with %d$ in your pocket.%n", totalMoney);
        } else {
            System.out.printf("You got caught with %d$, and you are going to jail.%n", totalMoney);
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }
}
