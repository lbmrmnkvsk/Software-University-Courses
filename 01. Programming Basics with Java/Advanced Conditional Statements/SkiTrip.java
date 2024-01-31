package Z_ProgrammingBasics.AdvancedConditionalStatementsExercise;
import java.util.Scanner;
public class SkiTrip_09 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int days = Integer.parseInt(scanner.nextLine());
        String type = scanner.nextLine();
        String grade = scanner.nextLine();
        double price = 0;
        switch (type) {
            case "room for one person":
                price = 18*(days-1);
                break;
            case "apartment":
                price = 25*(days-1);
                if ((days-1) < 10) {
                    price = 0.7*price;
                } else if ((days-1) <= 15) {
                    price = 0.65*price;
                } else if ((days-1) >15) {
                    price = 0.5*price;
                }
                break;
            case "president apartment":
                price = 35*(days-1);
                if ((days-1) <10) {
                    price = 0.9*price;
                } else if ((days-1) <= 15) {
                    price = 0.85*price;
                } else if ((days-1) > 15) {
                    price = 0.8*price;
                }
                break;
        }
        if (grade.equals("positive")) {
            price = price*1.25;
        } else if (grade.equals("negative")) {
            price = 0.9*price;
        }
        System.out.printf("%.2f", price);

    }
}
