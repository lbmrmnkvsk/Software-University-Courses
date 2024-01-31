package JavaAdvanced.Practice2;

import java.util.Scanner;

public class P02_01_StickyFingers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        String[] directions = scanner.nextLine().split(",");
        String[][] matrix = new String[size][size];

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

        boolean isCaught = false;
        int stolenMoney = 0;

        for (String direction : directions) {
            if (direction.equals("left")) {
                if (currentCol - 1 < 0) {
                    System.out.println("You cannot leave the town, there is police outside!");
                } else {
                    matrix[currentRow][currentCol] = "+";
                    currentCol--;
                }
            } else if (direction.equals("right")) {
                if (currentCol + 1 >= size) {
                    System.out.println("You cannot leave the town, there is police outside!");
                } else {
                    matrix[currentRow][currentCol] = "+";
                    currentCol++;
                }
            } else if (direction.equals("up")) {
                if (currentRow - 1 < 0) {
                    System.out.println("You cannot leave the town, there is police outside!");
                } else {
                    matrix[currentRow][currentCol] = "+";
                    currentRow--;
                }
            } else if (direction.equals("down")) {
                if (currentRow + 1 >= size) {
                    System.out.println("You cannot leave the town, there is police outside!");
                } else {
                    matrix[currentRow][currentCol] = "+";
                    currentRow++;
                }
            }

            String position = matrix[currentRow][currentCol];

            if (position.equals("+")) {
                matrix[currentRow][currentCol] = "D";
            } else if (position.equals("$")) {
                int currentMoney = currentRow * currentCol;
                stolenMoney += currentMoney;
                System.out.printf("You successfully stole %d$.%n", currentMoney);
                matrix[currentRow][currentCol] = "D";
            } else if (position.equals("P")) {
                isCaught = true;
                matrix[currentRow][currentCol] = "#";
                break;
            }
        }

        if (!isCaught) {
            System.out.printf("Your last theft has finished successfully with %d$ in your pocket.%n", stolenMoney);
        } else {
            System.out.printf("You got caught with %d$, and you are going to jail.%n", stolenMoney);
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }
}
