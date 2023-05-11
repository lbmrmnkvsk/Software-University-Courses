package JavaAdvanced.StacksQueuesLab;

import java.util.ArrayDeque;
import java.util.Scanner;

public class _01_01_BrowserHistory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<String> historyStack = new ArrayDeque<>();
        String command = scanner.nextLine();

        while (!command.equals("Home")) {
            if (command.equals("back")) {
                if (historyStack.size() > 1) {
                    historyStack.pop();
                    System.out.println(historyStack.peek());
                } else {
                    System.out.println("no previous URLs");
                }
            } else {
                historyStack.push(command);
                System.out.println(historyStack.peek());
            }

            command = scanner.nextLine();
        }
    }
}
