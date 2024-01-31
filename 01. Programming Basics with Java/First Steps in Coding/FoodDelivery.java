package Z_ProgrammingBasics.FirstStepsExercise;
import java.util.Scanner;
public class FoodDelivery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int chicken = Integer.parseInt(scanner.nextLine());
        int fish = Integer.parseInt(scanner.nextLine());
        int veg = Integer.parseInt(scanner.nextLine());
        double food = (chicken*10.35)+(fish*12.40)+(veg*8.15);
        double dessert = food*0.2;
        double total = food + dessert +2.50;
        System.out.println(total);
    }
}
