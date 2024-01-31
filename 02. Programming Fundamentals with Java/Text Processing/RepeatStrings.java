package ProgrammingFundamentals.TextProcessingLab;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class P02_01_RepeatStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputArray = scanner.nextLine().split("\\s+");
        List<String> repeatList = new ArrayList<>();

        for (int i = 0; i <= inputArray.length - 1; i++) {
            String word = inputArray[i];
            int repeatCount = inputArray[i].length();
            String repeatedWord = repeatWord(word, repeatCount);
            repeatList.add(repeatedWord);
        }
        System.out.println(String.join("", repeatList));
    }

    private static String repeatWord(String word, int n) {
        String result = "";
        for (int i = 1; i <= n; i++) {
            result = result + word;
        }
        return result;
    }
}
