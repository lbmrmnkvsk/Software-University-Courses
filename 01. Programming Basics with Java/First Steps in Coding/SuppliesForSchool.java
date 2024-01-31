package Z_ProgrammingBasics.FirstStepsExercise;
import java.util.Scanner;
public class SuppliesForSchool {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pens = Integer.parseInt(scanner.nextLine());
        int highlighters = Integer.parseInt(scanner.nextLine());
        int solution = Integer.parseInt(scanner.nextLine());
        int discount = Integer.parseInt(scanner.nextLine());
        double amountNoDiscount = (pens*5.80)+(highlighters*7.20)+(solution*1.20);
        double amountAfterDiscount = amountNoDiscount*(1-(discount/100.0));
        System.out.println(amountAfterDiscount);
    }
}
