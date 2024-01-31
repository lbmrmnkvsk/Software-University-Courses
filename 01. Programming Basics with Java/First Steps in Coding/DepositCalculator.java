package Z_ProgrammingBasics.FirstStepsExercise;
import java.util.Scanner;
public class DepositCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double amount = Double.parseDouble(scanner.nextLine());
        int months = Integer.parseInt(scanner.nextLine());
        double interestRate = Double.parseDouble(scanner.nextLine());

        double finalAmount = amount +months*((amount*(interestRate/100))/12);
        System.out.println(finalAmount);
    }
}
