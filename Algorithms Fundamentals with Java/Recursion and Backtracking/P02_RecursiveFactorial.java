package L01_Recursion_and_Backtracking;

import java.util.Scanner;

public class P02_RecursiveFactorial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        int factorial = factorial(n);
        System.out.println(factorial);
    }

    private static int factorial(int n) {
        if (n == 1) {
            return 1;
        }

        return n * factorial(n - 1);
    }
}
