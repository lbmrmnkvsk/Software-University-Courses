package ProgrammingFundamentals.MethodsLab;
import java.util.Scanner;
public class P10_01_MultiplyEvensByOdds {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());
        int n = Math.abs(num);
        System.out.println(getMultipleOfEvensAndOdds(n));
    }

    public static int getSumOfEvenDigits(int n) {
        int sumOfEvenDigits = 0;
        while(n > 0) {
            int lastDigit = n % 10;
            if (lastDigit % 2 == 0) {
                sumOfEvenDigits += lastDigit;
            }
            n = n / 10;
        }
        return sumOfEvenDigits;
    }

    public static int getSumOfOddDigits(int n) {
        int sumOfOddDigits = 0;
        while(n > 0) {
            int lastDigit = n % 10;
            if (lastDigit % 2 != 0) {
                sumOfOddDigits += lastDigit;
            }
            n = n / 10;
        }
        return sumOfOddDigits;
    }

    public static int getMultipleOfEvensAndOdds(int n) {
        int evenSum = getSumOfEvenDigits(n);
        int oddSum = getSumOfOddDigits(n);

        return evenSum * oddSum;
    }
}
