package JavaAdvanced.StacksQueuesLab;

import java.util.ArrayDeque;
import java.util.Scanner;

public class _03_01_DecimalToBinary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int number = Integer.parseInt(scanner.nextLine());

        if (number == 0) {
            System.out.print(0);
        }

        while (number > 0) {
            int binary = number % 2;
            stack.push(binary);
            number = number / 2;
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
    }
}
