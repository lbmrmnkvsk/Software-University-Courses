package Z_ProgrammingBasics.AdvancedConditionalStatementsExercise;
import java.util.Scanner;
public class FishingBoat_04 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int budget = Integer.parseInt(scanner.nextLine());
        String season = scanner.nextLine();
        int numFishers = Integer.parseInt(scanner.nextLine());
        double price = 0;
        if (season.equals("Spring")) {
            price = 3000;
        } else if (season.equals("Summer") || season.equals("Autumn")) {
            price = 4200;
        } else if (season.equals("Winter")) {
            price = 2600;
        }
        if (numFishers <= 6) {
            price = 0.9*price;
        } else if (numFishers <= 11){
            price = 0.85*price;
        } else if (numFishers >=12) {
            price = 0.75*price;
        }
        if (numFishers % 2 == 0 && !season.equals("Autumn")) {
            price = 0.95*price;
        }
        if (budget >= price) {
            System.out.printf("Yes! You have %.2f leva left.", (budget-price));
        } else {
            System.out.printf("Not enough money! You need %.2f leva.", (price-budget));
        }
    }
}
