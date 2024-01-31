package ProgrammingFundamentals.AssociativeArraysLab;
import java.util.*;

public class P03_01_OddOccurrences {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputArr = scanner.nextLine().split("\\s+");

        LinkedHashMap<String, Integer> wordsMap = new LinkedHashMap<>();

        for (String word : inputArr) {
            word = word.toLowerCase();
            if (!wordsMap.containsKey(word)) {
                wordsMap.put(word, 1);
            } else {
                int value = wordsMap.get(word);
                wordsMap.put(word, (value + 1));
            }
        }
        List<String> resultList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordsMap.entrySet()) {
            if (entry.getValue() % 2 == 1) {
                resultList.add(entry.getKey());
            }
        }
        System.out.print(String.join(", ", resultList));
    }
}
