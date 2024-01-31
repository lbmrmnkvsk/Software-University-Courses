package Z_ProgrammingBasics.AdvancedConditionalStatementsExercise;
import java.util.Scanner;
public class NewHouse_03 {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        String type = scanner.nextLine();
        int numberFlowers = Integer.parseInt(scanner.nextLine());
        int budget = Integer.parseInt(scanner.nextLine());
        double price = 0;

        switch (type) {
            case "Roses":
                price = 5 * numberFlowers;
                if (numberFlowers > 80) {
                    price =0.9*price;
                }
                break;
            case "Dahlias":
                price = 3.8*numberFlowers;
                if (numberFlowers > 90) {
                    price = 0.85*price;
                }
                break;
            case "Tulips":
                price = 2.8*numberFlowers;
                if (numberFlowers > 80) {
                    price = 0.85*price;
                }
                break;
            case "Narcissus":
                price = 3*numberFlowers;
                if ( numberFlowers < 120) {
                    price = 1.15*price;
                }
                break;
            case "Gladiolus":
                price = 2.5*numberFlowers;
                if (numberFlowers < 80) {
                    price = 1.2*price;
                }
                break;
        }
        if(budget >= price) {
            System.out.printf("Hey, you have a great garden with %d %s and %.2f leva left.",
                    numberFlowers, type, (budget-price));
        } else {
            System.out.printf("Not enough money, you need %.2f leva more.",
                    (price-budget));
        }
    }
}