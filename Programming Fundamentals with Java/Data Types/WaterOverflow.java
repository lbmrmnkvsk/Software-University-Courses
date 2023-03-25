package ProgrammingFundamentals.DataTypesExercise;
import java.util.Scanner;
public class P07_01_WaterOverflow {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int litersInTank = 0;
        int capacityLeft = 255;

        for (int i = 1; i <= n; i++) {
            int liters = Integer.parseInt(scanner.nextLine());
            if (liters <= capacityLeft) {
                capacityLeft = capacityLeft - liters;
                litersInTank = litersInTank + liters;
            } else {
                System.out.println("Insufficient capacity!");
            }
        }
        System.out.println(litersInTank);

    }
}
