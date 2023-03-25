package ProgrammingFundamentals.TextProcessingLab;
import java.util.Scanner;
public class P01_01_ReverseStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();

        while (!command.equals("end")) {
            String word = command;
            String reversedWord = "";
            for (int i = word.length() - 1; i >= 0; i--) {
                char currentSymbol = word.charAt(i);
                reversedWord = reversedWord + currentSymbol;
            }
            System.out.printf("%s = %s%n", word, reversedWord);

            command = scanner.nextLine();
        }
    }
}
