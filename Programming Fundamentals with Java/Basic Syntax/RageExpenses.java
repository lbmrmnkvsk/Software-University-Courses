package ProgrammingFundamentals.BasicSyntaxExercise;
import java.util.Scanner;
public class P11RageExpenses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lostGames = Integer.parseInt(scanner.nextLine());
        double headsetPrice = Double.parseDouble(scanner.nextLine());
        double mousePrice = Double.parseDouble(scanner.nextLine());
        double keyboardPrice = Double.parseDouble(scanner.nextLine());
        double displayPrice = Double.parseDouble(scanner.nextLine());

        int countHeadset = lostGames/2;
        int countMouse = lostGames/3;
        int countKeyboard = lostGames/6;
        int countDisplay = lostGames/12;

        double expense = (headsetPrice*countHeadset) + (mousePrice*countMouse) +
                (keyboardPrice*countKeyboard) + (displayPrice*countDisplay);

        System.out.printf("Rage expenses: %.2f lv.", expense);
    }
}
