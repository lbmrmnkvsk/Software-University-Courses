package Z_ProgrammingBasics.FirstStepsExercise;
import java.util.Scanner;
public class VacationBooksList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numPages = Integer.parseInt(scanner.nextLine());
        int pagesPerHour = Integer.parseInt(scanner.nextLine());
        int days = Integer.parseInt(scanner.nextLine());

        int hours = numPages/(pagesPerHour*days);
        System.out.println(hours);
    }
}
