package JavaAdvanced.z_Practice.StacksQueues;
import java.util.ArrayDeque;
import java.util.Scanner;
public class P01_02_BrowserHistory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();
        ArrayDeque<String> stack = new ArrayDeque<>();

        while (!command.equals("Home")) {
            if (command.equals("back")) {
                if (stack.size() == 0 || stack.size() == 1) {
                    System.out.println("no previous URLs");
                } else {
                    stack.pop();
                    System.out.println(stack.peek());
                }
            } else {
                stack.push(command);
                System.out.println(stack.peek());
            }

            command = scanner.nextLine();
        }
    }
}
