package ProgrammingFundamentals.ArraysExercise;
import java.util.Arrays;
import java.util.Scanner;
public class P06_01_EqualSums {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbersArray = Arrays
                .stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int index = 0; index <= numbersArray.length - 1; index++) {
            int leftSum = 0;
            int rightSum = 0;
            for (int leftIndex = 0; leftIndex < index; leftIndex++) {
                leftSum += numbersArray[leftIndex];
            }
            for (int rightIndex = index + 1; rightIndex <= numbersArray.length - 1; rightIndex++) {
                rightSum += numbersArray[rightIndex];
            }
            if (leftSum == rightSum) {
                System.out.println(index);
                return;
            }
        }
        System.out.println("no");
    }
}
