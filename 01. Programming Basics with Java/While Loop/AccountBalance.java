package Z_ProgrammingBasics.WhileLoopLab;
import java.util.Scanner;
public class AccountBalance_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        double total = 0;
        while(!input.equals("NoMoreMoney")) {
            double increase = Double.parseDouble(input);
            if(increase < 0) {
                System.out.println("Invalid operation!");
                break;
            }
            total += increase;
            System.out.printf("Increase: %.2f%n", increase);
            input = scanner.nextLine();
        }
        System.out.printf("Total: %.2f", total);
    }
}
