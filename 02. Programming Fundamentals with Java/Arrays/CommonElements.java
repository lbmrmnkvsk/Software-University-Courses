package ProgrammingFundamentals.ArraysExercise;
import java.util.Scanner;
public class P02_01_CommonElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] firstArray = scanner.nextLine().split(" ");
        String[] secondArray = scanner.nextLine().split(" ");

        for (String secondElement : secondArray) {
            for (String firstElement : firstArray) {
                if (secondElement.equals(firstElement)) {
                    System.out.print(secondElement + " ");
                    break;
                }
            }
        }
    }
}
