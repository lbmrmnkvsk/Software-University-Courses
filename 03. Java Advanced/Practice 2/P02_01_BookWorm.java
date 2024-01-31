package JavaAdvanced.Practice2;

import java.util.Scanner;

public class P02_01_BookWorm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String initialString = scanner.nextLine();
        StringBuilder sb = new StringBuilder(initialString);
        int size = Integer.parseInt(scanner.nextLine());
        String[][] matrix = new String[size][size];

        for (int row = 0; row < matrix.length; row++) {
            String[] arr = scanner.nextLine().split("");
            matrix[row] = arr;
        }

        int currentRow = -1;
        int currentCol = -1;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("P")) {
                    currentRow = row;
                    currentCol = col;
                }
            }
        }

        String command = scanner.nextLine();

        while (!command.equals("end")) {
            String direction = command;
            matrix[currentRow][currentCol] = "-";

            if (direction.equals("up")) {
                if (currentRow - 1 < 0) {
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                } else {
                    currentRow--;
                }
            } else if (direction.equals("down")) {
                if (currentRow + 1 >= size) {
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                } else {
                    currentRow++;
                }
            } else if (direction.equals("left")) {
                if (currentCol - 1 < 0) {
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                } else {
                    currentCol--;
                }
            } else if (direction.equals("right")) {
                if (currentCol + 1 >= size) {
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                } else {
                    currentCol++;
                }
            }

            String position = matrix[currentRow][currentCol];

            if (position.equals("-")) {
                matrix[currentRow][currentCol] = "P";
            } else {
                sb.append(position);
                matrix[currentRow][currentCol] = "P";
            }

            command = scanner.nextLine();
        }

        System.out.println(sb.toString());
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}
