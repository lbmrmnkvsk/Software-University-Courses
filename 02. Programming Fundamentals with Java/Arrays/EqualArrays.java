package ProgrammingFundamentals.ArraysLab;
import java.util.Arrays;
import java.util.Scanner;
public class P06_01_EqualArrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] firstArr = Arrays
                .stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] secondArr = Arrays
                .stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        boolean isIdentical = true;
        int sum = 0;
        int diffIndex = -1;

        for (int i = 0; i < firstArr.length; i++) {
            int firstNumber = firstArr[i];
            int secondNumber = secondArr[i];
            if (firstNumber == secondNumber) {
                sum += firstNumber;
            } else {
                isIdentical = false;
                diffIndex = i;
                break;
            }
        }
        if (isIdentical) {
            System.out.printf("Arrays are identical. Sum: %d", sum);
        } else {
            System.out.printf("Arrays are not identical. Found difference at %d index.", diffIndex);
        }
    }
}
