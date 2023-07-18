package JavaOOP.Exceptions;

import java.util.Scanner;

public class P01_01_NumberInRange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");
        int start = Integer.parseInt(tokens[0]);
        int end = Integer.parseInt(tokens[1]);
        System.out.printf("Range: [%d...%d]%n", start, end);

        while (true) {
            String input = scanner.nextLine();

            try {
                int number = Integer.parseInt(input);
                if (number >= start && number <= end) {
                    System.out.printf("Valid number: %d%n", number);
                    break;
                } else {
                    System.out.printf("Invalid number: %d%n", number);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Invalid number: %s%n", input);
            }
        }
    }
}
