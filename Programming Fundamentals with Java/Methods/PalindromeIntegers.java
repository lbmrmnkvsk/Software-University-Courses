package ProgrammingFundamentals.MethodsExercise;
import java.util.Scanner;
public class P09_01_PalindromeIntegers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine();
        while (!number.equals("END")) {
            if (isPalindrome(number)) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }

            number = scanner.nextLine();
        }
    }

    public static boolean isPalindrome(String text) {
        String reversedText = "";
        for (int i = text.length() - 1; i >= 0; i--) {
            reversedText = reversedText + text.charAt(i);
        }
        return text.equals(reversedText);
    }
}
