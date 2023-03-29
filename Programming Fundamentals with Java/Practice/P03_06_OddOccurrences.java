package ProgrammingFundamentals.AssociativeArraysLab;
import java.util.*;

public class P03_06_OddOccurrences {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] words = scanner.nextLine().split("\\s+");
        LinkedHashMap<String, Integer> count = new LinkedHashMap<>();

        for (String word : words) {
            word = word.toLowerCase();
            if (!count.containsKey(word)) {
                count.put(word, 1);
            } else {
                int value = count.get(word);
                count.put(word, (value + 1));
            }
        }

        List<String> oddOccurrences = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            if (entry.getValue() % 2 == 1) {
                oddOccurrences.add(entry.getKey());
            }
        }

        System.out.print(String.join(", ", oddOccurrences));
    }
}
