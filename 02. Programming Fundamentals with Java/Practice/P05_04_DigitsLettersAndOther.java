package ProgrammingFundamentals.TextProcessingLab;
import java.util.Scanner;
public class P05_04_DigitsLettersAndOther {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();
        StringBuilder digitsSb = new StringBuilder();
        StringBuilder lettersSb = new StringBuilder();
        StringBuilder otherSb = new StringBuilder();

        for (char symbol : text.toCharArray()) {
            if (Character.isDigit(symbol)) {
                digitsSb.append(symbol);
            } else if (Character.isLetter(symbol)) {
                lettersSb.append(symbol);
            } else {
                otherSb.append(symbol);
            }
        }

        System.out.println(digitsSb);
        System.out.println(lettersSb);
        System.out.print(otherSb);
    }
}
