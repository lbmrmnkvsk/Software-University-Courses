package ProgrammingFundamentals.TextProcessingLab;
import java.util.Scanner;
public class P02_04_RepeatStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] words = scanner.nextLine().split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            int n = word.length();
            for (int i = 1; i <= n; i++) {
                result.append(word);
            }
        }

        System.out.print(result);
    }
}
