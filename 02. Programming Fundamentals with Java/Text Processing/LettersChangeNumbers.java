package ProgrammingFundamentals.TextProcessingExercise;
import java.util.Scanner;
public class P08_01_LettersChangeNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] codes = scanner.nextLine().split("\\s+");
        double totalSum = 0;

        for (String code : codes) {
            double currentNumber = getSum(code);
            totalSum += currentNumber;
        }
        System.out.printf("%.2f", totalSum);
    }

    private static double getSum(String code) {
        char letterBefore = code.charAt(0);
        char letterAfter = code.charAt(code.length() - 1);
        double number = Double.parseDouble(code.substring(1, code.length() - 1));

        if (Character.isUpperCase(letterBefore)) {
            int upperLetterPosition = (int) (letterBefore - 64);
            number = number / upperLetterPosition;
        } else {
            int lowerLetterPosition = (int) (letterBefore - 96);
            number = number * lowerLetterPosition;
        }

        if (Character.isUpperCase(letterAfter)) {
            int upperLetterPosition = (int) (letterAfter - 64);
            number = number - upperLetterPosition;
        } else {
            int lowerLetterPosition = (int) (letterAfter - 96);
            number = number + lowerLetterPosition;
        }

        return number;
    }
}
