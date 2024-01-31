package Z_ProgrammingBasics.AdvancedConditionalStatementsLab;
import java.util.Scanner;
public class WeekendOrWorkingDay_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String day = scanner.nextLine();
        String message = "";

        switch (day) {
            case "Monday":
            case "Tuesday":
            case "Wednesday":
            case "Thursday":
            case "Friday":
                message = "Working day";
                break;
            case "Saturday":
            case "Sunday":
                message = "Weekend";
                break;
            default:
                message = "Error";

        }
        System.out.println(message);
    }
}
