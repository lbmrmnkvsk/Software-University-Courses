package ProgrammingFundamentals.BasicSyntaxExercise;
import java.util.Scanner;
public class P10PadawanEquipment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double budget = Double.parseDouble(scanner.nextLine());
        int students = Integer.parseInt(scanner.nextLine());
        double priceLightsaber = Double.parseDouble(scanner.nextLine());
        double priceRobe = Double.parseDouble(scanner.nextLine());
        double priceBelt = Double.parseDouble(scanner.nextLine());

        double numLightsabers = Math.ceil(1.1*students);
        int freeBelts = students/6;
        int numBelts = students - freeBelts;
        int numRobes = students;

        double totalPrice = (numLightsabers*priceLightsaber) + (numRobes*priceRobe) +
                (numBelts*priceBelt);

        if (budget >= totalPrice) {
            System.out.printf("The money is enough - it would cost %.2flv.", totalPrice);
        } else {
            System.out.printf("George Lucas will need %.2flv more.",
                    Math.abs(totalPrice-budget));
        }
    }
}
