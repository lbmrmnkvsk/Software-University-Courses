package ProgrammingFundamentals.TextProcessingLab;
import java.util.Scanner;
public class P01_04_ReverseStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();

        while (!command.equals("end")) {
            StringBuilder word = new StringBuilder(command);
            StringBuilder reversedWord = word.reverse();
            System.out.printf("%s = %s%n", command, reversedWord);

            command = scanner.nextLine();
        }
    }
}
