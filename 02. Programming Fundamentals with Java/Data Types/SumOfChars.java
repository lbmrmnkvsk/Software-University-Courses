package ProgrammingFundamentals.DataTypesExercise;
import java.util.Scanner;
public class P04_01_SumOfChars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int sum = 0;

        for (int i = 1; i <= n; i++){
            char symbol = scanner.nextLine().charAt(0);
            int asciiCode = (int) symbol;
            sum += asciiCode;
        }
        System.out.printf("The sum equals: %d", sum);
    }
}
