package ProgrammingFundamentals.ArraysExercise;
import java.util.Arrays;
import java.util.Scanner;
public class P08_01_MagicSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbersArray = Arrays
                .stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int targetSum = Integer.parseInt(scanner.nextLine());

        for (int index = 0; index <= numbersArray.length - 1; index++) {
            int currentNumber = numbersArray[index];

            for (int i = index + 1; i <= numbersArray.length - 1; i++) {
                int nextNumber = numbersArray[i];
                if (currentNumber + nextNumber == targetSum) {
                    System.out.println(currentNumber + " " + nextNumber);
                }

            }
        }
    }
}
