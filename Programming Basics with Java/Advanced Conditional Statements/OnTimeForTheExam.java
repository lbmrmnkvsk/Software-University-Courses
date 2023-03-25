package Z_ProgrammingBasics.AdvancedConditionalStatementsExercise;
import java.util.Scanner;
public class OnTimeForTheExam_08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int hourExam = Integer.parseInt(scanner.nextLine());
        int minuteExam = Integer.parseInt(scanner.nextLine());
        int hourArrival = Integer.parseInt(scanner.nextLine());
        int minuteArrival = Integer.parseInt(scanner.nextLine());
        int totalMinutesExam = (60*hourExam)+minuteExam;
        int totalMinutesArrival = (60*hourArrival)+minuteArrival;

        if (totalMinutesExam == totalMinutesArrival) {
            System.out.println("On time");
        }
    }
}
