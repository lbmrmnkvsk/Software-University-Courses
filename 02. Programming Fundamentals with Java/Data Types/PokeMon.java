package ProgrammingFundamentals.DataTypesExercise;
import java.util.Scanner;
public class P10_01_PokeMon {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int power = Integer.parseInt(scanner.nextLine());
        int distance = Integer.parseInt(scanner.nextLine());
        int factor = Integer.parseInt(scanner.nextLine());
        int startPower = power;
        int count = 0;

        while (power >= distance) {
            power = power - distance;
            count++;
            if (power == startPower/2) {
                if (factor != 0) {
                    power = power / factor;
                }
            }
        }
        System.out.println(power);
        System.out.println(count);
    }
}
