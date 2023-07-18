package JavaOOP.Exceptions;

import java.util.Scanner;

public class P02_01_SquareRoot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        try {
            int number = Integer.parseInt(input);
            if (number < 0) {
                throw new IllegalArgumentException("Invalid");
            } else {
                System.out.printf("%.2f%n", Math.sqrt(number));
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid");
        } finally {
            System.out.println("Goodbye");
        }
    }
}
