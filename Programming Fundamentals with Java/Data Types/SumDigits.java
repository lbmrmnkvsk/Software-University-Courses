package ProgrammingFundamentals.DataTypesExercise;
import java.util.Scanner;
public class P02_01_SumDigits {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int initialNumber = Integer.parseInt(scanner.nextLine());
        int num = initialNumber;
        int sum = 0;

        while(num > 0) {
            int lastDigit = num % 10;
            sum += lastDigit;
            num = num / 10;
        }
        System.out.println(sum);

    }
}
