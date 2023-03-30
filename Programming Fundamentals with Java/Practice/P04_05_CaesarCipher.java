package ProgrammingFundamentals.TextProcessingExercise;
import java.util.Scanner;
public class P04_05_CaesarCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();
        StringBuilder encryptedText = new StringBuilder();

        for (char symbol : text.toCharArray()) {
            char encryptedSymbol = (char) (symbol + 3);
            encryptedText.append(encryptedSymbol);
        }

        System.out.print(encryptedText);
    }
}
