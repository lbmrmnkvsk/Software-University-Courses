package JavaAdvanced.z_Practice.StacksQueuesExercise;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;
public class P03_02_MaximumElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            String command = scanner.nextLine();
            String[] tokens = command.split("\\s+");

            if (command.startsWith("1")) {
                int element = Integer.parseInt(tokens[1]);
                stack.push(element);
            } else if (command.startsWith("2")) {
                stack.pop();
            } else if (command.startsWith("3")) {
                System.out.println(Collections.max(stack));
            }
        }
    }
}
