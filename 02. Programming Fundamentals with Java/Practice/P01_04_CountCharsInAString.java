package ProgrammingFundamentals.AssociativeArraysExercise;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
public class P01_04_CountCharsInAString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        LinkedHashMap<Character, Integer> count = new LinkedHashMap<>();

        for (char symbol : input.toCharArray()) {
            if (symbol != ' ') {
                if (!count.containsKey(symbol)) {
                    count.put(symbol, 1);
                } else {
                    int value = count.get(symbol);
                    count.put(symbol, (value + 1));
                }
            }
        }

        for (Map.Entry<Character, Integer> entry : count.entrySet()) {
            System.out.printf("%c -> %d%n", entry.getKey(), entry.getValue());
        }
    }
}
