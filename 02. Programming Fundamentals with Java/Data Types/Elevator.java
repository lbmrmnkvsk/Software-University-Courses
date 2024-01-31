package ProgrammingFundamentals.DataTypesExercise;
import java.util.Scanner;
public class P03_01_Elevator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int p = Integer.parseInt(scanner.nextLine());
        double courses = Math.ceil((1.0*n)/p);
        System.out.printf("%.0f", courses);
    }
}
