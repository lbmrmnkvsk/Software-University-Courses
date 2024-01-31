package JavaAdvanced.z_Practice.StacksQueues;
import java.util.ArrayDeque;
import java.util.Scanner;
public class P01_01_BrowserHistory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        ArrayDeque<String> stack = new ArrayDeque<>();

        while (!input.equals("Home")) {
            if (input.equals("back")) {
                if (stack.size() == 0 || stack.size() == 1) {
                    System.out.println("no previous URLs");
                } else {
                    stack.pop();
                    System.out.println(stack.peek());
                }
            } else {
                stack.push(input);
                System.out.println(stack.peek());
            }

            input = scanner.nextLine();
        }
    }
}
