package JavaAdvanced.z_Practice.StacksQueuesLab;
import java.util.ArrayDeque;
import java.util.Scanner;
public class P04_02_MatchingBrackets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        ArrayDeque<Integer> indices = new ArrayDeque<>();

        for (int i = 0; i <= input.length() - 1; i++) {
            char ch = input.charAt(i);

            if (ch == '(') {
                indices.push(i);
            } else if (ch == ')') {
                int startIndex = indices.pop();
                String contents = input.substring(startIndex, i + 1);
                System.out.println(contents);
            }
        }
    }
}
