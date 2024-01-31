package L01_Recursion_and_Backtracking;

import java.util.Scanner;

public class P04_Generating_0_1_Vectors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine()); // User inputs the size of the vector

        int[] vector = new int[n];
        generateVectors(vector, 0);
    }

    private static void generateVectors(int[] vector, int index) {
        if (index >= vector.length) {
            printVector(vector);
            return;
        }

        for (int i = 0; i <= 1; i++) {
            vector[index] = i;
            generateVectors(vector, index + 1);
        }
    }

    private static void printVector(int[] vector) {
        for (int i : vector) {
            System.out.print(i);
        }
        System.out.println();
    }
}
