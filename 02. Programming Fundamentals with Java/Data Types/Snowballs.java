package ProgrammingFundamentals.DataTypesExercise;
import java.util.Scanner;
public class P11_01_Snowballs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.nextLine());

        double maxSnowballValue = Double.MIN_VALUE;
        int maxSnowballSnow = Integer.MIN_VALUE;
        int maxSnowballTime = Integer.MIN_VALUE;
        int maxSnowballQuality = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++) {
            int snowballSnow = Integer.parseInt(scanner.nextLine());
            int snowballTime = Integer.parseInt(scanner.nextLine());
            int snowballQuality = Integer.parseInt(scanner.nextLine());
            double snowballValue = Math.pow((snowballSnow/snowballTime), snowballQuality);

            if (snowballValue > maxSnowballValue) {
                maxSnowballValue = snowballValue;
                maxSnowballSnow = snowballSnow;
                maxSnowballTime = snowballTime;
                maxSnowballQuality = snowballQuality;
            }
        }
        System.out.printf("%d : %d = %.0f (%d)", maxSnowballSnow, maxSnowballTime,
                maxSnowballValue, maxSnowballQuality);
    }
}
