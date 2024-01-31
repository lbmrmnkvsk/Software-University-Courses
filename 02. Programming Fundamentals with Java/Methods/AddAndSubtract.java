package ProgrammingFundamentals.MethodsExercise;
import java.util.Scanner;
public class P05_01_AddAndSubtract {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number1 = Integer.parseInt(scanner.nextLine());
        int number2 = Integer.parseInt(scanner.nextLine());
        int number3 = Integer.parseInt(scanner.nextLine());
        int result1 = sum(number1, number2);
        int result2 = subtract(result1, number3);
        System.out.println(result2);
    }

    public static int sum(int num1, int num2) {
        return (num1+num2);
    }

    public static int subtract(int num1, int num2) {
        return (num1 - num2);
    }
}
