package ProgrammingFundamentals.TextProcessingLab;
import java.util.Scanner;
public class P04_04_TextFilter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] bannedWords = scanner.nextLine().split(", ");
        String text = scanner.nextLine();

        for (String word : bannedWords) {
            String asterisk = getAsterisk(word);
            text = text.replace(word, asterisk);
        }

        System.out.print(text);
    }

    private static String getAsterisk(String word) {
        StringBuilder asterisk = new StringBuilder();

        for (int i = 1; i <= word.length(); i++) {
            asterisk.append('*');
        }

        return asterisk.toString();
    }
}
