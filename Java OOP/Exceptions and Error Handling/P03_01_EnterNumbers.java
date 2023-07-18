package JavaOOP.Exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P03_01_EnterNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int start = 1;
        int end = 100;
        List<Integer> numbers = new ArrayList<>();

        while (numbers.size() < 10) {
            int number = readNumber(start, end, scanner);
            if (number == 0) {
                continue;
            }
            start = number;
            numbers.add(number);
        }

        System.out.print(numbers.toString().replace("[","").replace("]",""));
    }

    private static int readNumber(int start, int end, Scanner scanner) {
        int number = 0;

        try {
            number = Integer.parseInt(scanner.nextLine());
            if (number <= start || number >= end) {
                System.out.printf("Your number is not in range %d - 100!%n", start);
                number = 0;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Number!");
        }

        return number;
    }
}
