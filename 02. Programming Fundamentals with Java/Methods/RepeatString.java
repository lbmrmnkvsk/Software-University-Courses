package ProgrammingFundamentals.MethodsLab;
import java.util.Scanner;
public class P07_01_RepeatString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputText = scanner.nextLine();
        int n = Integer.parseInt(scanner.nextLine());

        System.out.println(repeatString(inputText, n));
    }

    public static String repeatString(String text, int n) {
        String result = "";
        for (int i = 1; i <= n; i++) {
            result = result + text;
        }
        return result;
    }
}
