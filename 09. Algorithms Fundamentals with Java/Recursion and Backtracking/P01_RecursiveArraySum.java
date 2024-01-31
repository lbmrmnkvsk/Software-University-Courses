package L01_Recursion_and_Backtracking;

import java.util.Arrays;
import java.util.Scanner;

public class P01_RecursiveArraySum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        int sum = sumNumbers(array, 0);
        System.out.println(sum);
    }

    private static int sumNumbers(int[] array, int index) {
        if (index >= array.length) {
            return 0;
        }

        return array[index] + sumNumbers(array, index + 1);
    }
}
