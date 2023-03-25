package ProgrammingFundamentals.ArraysExercise;
import java.util.Scanner;
public class P01_01_Train {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] numbersArr = new int[n];
        for (int i = 0; i < n; i++) {
            numbersArr[i] = Integer.parseInt(scanner.nextLine());
        }
        int sum = 0;
        for (int i = 0; i < numbersArr.length; i++) {
            int currentNumber = numbersArr[i];
            sum += currentNumber;
            System.out.printf("%d ", currentNumber);
        }
        System.out.printf("%n%d", sum);
    }
}
