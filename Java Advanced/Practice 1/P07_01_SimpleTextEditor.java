package JavaAdvanced.z_Practice.StacksQueuesExercise;
import java.util.ArrayDeque;
import java.util.Scanner;
public class P07_01_SimpleTextEditor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        StringBuilder textSb = new StringBuilder();
        ArrayDeque<String> textStack = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            String command = scanner.nextLine();
            String[] tokens = command.split("\\s+");

            if (command.startsWith("1")) {
                textStack.push(textSb.toString());

                String string = tokens[1];
                textSb.append(string);
            } else if (command.startsWith("2")) {
                textStack.push(textSb.toString());

                int count = Integer.parseInt(tokens[1]);
                // length 5, delete 2
                textSb.delete(textSb.length() - count, textSb.length());
            } else if (command.startsWith("3")) {
                int index = Integer.parseInt(tokens[1]);
                System.out.println(textSb.charAt(index - 1));
            } else if (command.startsWith("4")) {
                if (!textStack.isEmpty()) {
                    String lastText = textStack.pop();
                    textSb = new StringBuilder(lastText);
                }
            }
        }
    }
}
