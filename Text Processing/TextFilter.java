package ProgrammingFundamentals.TextProcessingLab;
import java.util.Scanner;
public class P04_01_TextFilter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] bannedWordsArray = scanner.nextLine().split(", ");
        String text = scanner.nextLine();

        for (String bannedWord : bannedWordsArray) {
            String asterisk = repeatString(bannedWord.length());
            text = text.replace(bannedWord, asterisk);
        }
        System.out.print(text);
    }

    private static String repeatString(int count) {
        String result = "";
        for (int i = 1; i <= count; i++) {
            result = result + "*";
        }
        return result;
    }
}
