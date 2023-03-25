package Z_ProgrammingBasics.AdvancedConditionalStatementsExercise;
import java.util.Scanner;
public class Journey_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double budget = Double.parseDouble(scanner.nextLine());
        String season = scanner.nextLine();
        double price = 0;
        String destination = "";
        String accommodation = "";
        if (budget <= 100) {
            destination = "Bulgaria";
            if(season.equals("summer")) {
                price = 0.3*budget;
                accommodation = "Camp";
            } else if(season.equals("winter")) {
                price = 0.7*budget;
                accommodation = "Hotel";
            }
        } else if (budget <= 1000) {
            destination = "Balkans";
            if (season.equals("summer")) {
                price = 0.4*budget;
                accommodation = "Camp";
            } else if (season.equals("winter")) {
                price = 0.8*budget;
                accommodation = "Hotel";
            }
        } else if (budget > 1000) {
            destination = "Europe";
            accommodation = "Hotel";
            price = 0.9*budget;
        }
        System.out.printf("Somewhere in %s%n", destination);
        System.out.printf("%s - %.2f", accommodation, price);
    }
}
