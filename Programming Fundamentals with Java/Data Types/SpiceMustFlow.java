package ProgrammingFundamentals.DataTypesExercise;
import java.util.Scanner;
public class P09_01_SpiceMustFlow {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int yield = Integer.parseInt(scanner.nextLine());
        int totalYield = 0;
        int days = 0;

        while(yield >= 100) {
            totalYield = totalYield + (yield - 26);
            days++;
            yield = yield - 10;
        }
        if (days > 0) {
            totalYield = totalYield - 26;
        }
        System.out.println(days);
        System.out.println(totalYield);
    }
}
