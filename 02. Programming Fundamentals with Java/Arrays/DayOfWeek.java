package ProgrammingFundamentals.ArraysLab;
import java.util.Scanner;
public class P01_01_DayOfWeek {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        String[] dayOfWeekArr = {
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday",
                "Sunday"
        };
        if (n >= 1 && n <= 7) {
            System.out.println(dayOfWeekArr[n - 1]);
        } else {
            System.out.println("Invalid day!");
        }
    }
}
