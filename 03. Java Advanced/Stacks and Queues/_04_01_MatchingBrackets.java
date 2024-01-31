package JavaAdvanced.z_Practice.StacksQueuesLab;
import java.util.ArrayDeque;
import java.util.Scanner;
public class P04_01_MatchingBrackets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        ArrayDeque<Integer> indices = new ArrayDeque<>();

        for (int i = 0; i <= input.length() - 1; i++) {
            if (input.charAt(i) == '(') {
                indices.push(i);
            } else if (input.charAt(i) == ')') {
                int openBracketIndex = indices.pop();
                String expression = input.substring(openBracketIndex, i + 1);
                System.out.println(expression);
            }
        }
    }
}
