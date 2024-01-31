package Z_ProgrammingBasics.ConditionalStatementsExcercise;
import java.util.Scanner;
public class GodzillaKong {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double filmBudget = Double.parseDouble(scanner.nextLine());
        int broiStatisti = Integer.parseInt(scanner.nextLine());
        double tsenaObleklo = Double.parseDouble(scanner.nextLine());
        double dekor =0.1*filmBudget;
        if (broiStatisti > 150) {
            tsenaObleklo = tsenaObleklo*0.9;
        }
        double obleklo = tsenaObleklo*broiStatisti;
        double tsena = dekor + obleklo;

        if (tsena > filmBudget) {
            System.out.printf("Not enough money!%nWingard needs %.2f leva more.", (tsena-filmBudget));
        } else {
            System.out.printf("Action!%nWingard starts filming with %.2f leva left.", (filmBudget-tsena));
        }
    }
}
