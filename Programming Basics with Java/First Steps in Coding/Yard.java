package Z_ProgrammingBasics.FirstSteps;

import java.util.Scanner;

public class Yard {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double area = Double.parseDouble(scanner.nextLine());
        double finalPrice=0.82*7.61*area;
        double discount = 0.18*7.61*area;
        System.out.printf("The final price is: %f lv.%nThe discount is: %f lv.", finalPrice, discount);
    }
}
