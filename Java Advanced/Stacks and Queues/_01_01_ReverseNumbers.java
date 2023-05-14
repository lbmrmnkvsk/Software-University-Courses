package JavaAdvanced.z_Practice.StacksQueuesExercise;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
public class P01_01_ReverseNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numbers = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int number : numbers) {
            stack.push(number);
        }

        while (!stack.isEmpty()) {
            int currentNumber = stack.pop();
            System.out.print(currentNumber + " ");
        }
    }
}
