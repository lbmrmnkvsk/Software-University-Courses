package JavaAdvanced.z_Practice.StacksQueues;
import java.util.*;
import java.util.stream.Collectors;

public class P02_02_SimpleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<String> stack = new ArrayDeque<>();
        List<String> expressionParts = Arrays.stream(scanner.nextLine().split("\\s+")).collect(Collectors.toList());
        Collections.reverse(expressionParts);

        for (String part : expressionParts) {
            stack.push(part);
        }

        while (stack.size() > 1) {
            int first = Integer.parseInt(stack.pop());
            String op = stack.pop();
            int second = Integer.parseInt(stack.pop());
            int result = 0;

            if (op.equals("+")) {
                result = first + second;
            } else if (op.equals("-")) {
                result = first - second;
            }
            stack.push(result + "");
        }
        System.out.println(stack.pop());
    }
}
