package Z_ProgrammingBasics.NestedLoops;
import java.util.Scanner;
public class Travelling_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String destination = scanner.nextLine();
        while(!destination.equals("End")) {
            double budget = Double.parseDouble(scanner.nextLine());
            double neededMoney = 0;
            while (neededMoney < budget) {
                 double money = Double.parseDouble(scanner.nextLine());
                 neededMoney += money;
            }
            System.out.printf("Going to %s!%n", destination);
            destination = scanner.nextLine();
        }
    }
}
