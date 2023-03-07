package ProgrammingFundamentals.AssociativeArraysExercise;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
public class P01_01_CountCharsInAString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();

        LinkedHashMap<Character, Integer> charCount = new LinkedHashMap<>();

        for (char symbol : text.toCharArray()) {
            if (symbol == ' ') {
                continue;
            }
            if (!charCount.containsKey(symbol)) {
                charCount.put(symbol, 1);
            } else {
                int value = charCount.get(symbol);
                charCount.put(symbol, (value + 1));
            }
        }

        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            System.out.printf("%c -> %d%n", entry.getKey(), entry.getValue());
        }
    }
}
