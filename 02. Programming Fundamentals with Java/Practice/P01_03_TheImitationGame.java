package ProgrammingFundamentals.FinalExamPrep;
import java.util.Scanner;
public class P01_03_TheImitationGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder message = new StringBuilder(scanner.nextLine());
        String command = scanner.nextLine();

        while (!command.equals("Decode")) {
            String[] tokens = command.split("\\|");

            if (command.startsWith("Move")) {
                int numberOfLetters = Integer.parseInt(tokens[1]);
                String lettersToMove = message.substring(0, numberOfLetters);
                message.delete(0, numberOfLetters);
                message.append(lettersToMove);
            } else if (command.startsWith("Insert")) {
                int index = Integer.parseInt(tokens[1]);
                String value = tokens[2];
                message.insert(index, value);
            } else if (command.startsWith("ChangeAll")) {
                String substring = tokens[1];
                String replacement = tokens[2];
                String text = message.toString();
                text = text.replace(substring, replacement);
                message = new StringBuilder(text);
            }

            command = scanner.nextLine();
        }

        System.out.printf("The decrypted message is: %s", message);
    }
}
