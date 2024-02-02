package L04_Recursion_and_Combinatorics_Exercise;

import java.util.Scanner;

public class P01_ReverseArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");
        String[] result = new String[input.length];
        reverse(0, input, result);
        System.out.println(String.join(" ", result));
    }

    public static void reverse(int index, String[] input, String[] result) {
        if (index > input.length - 1) {
            return;
        }

        result[index] = input[input.length - 1 - index];
        reverse(index + 1, input, result);
    }
}
