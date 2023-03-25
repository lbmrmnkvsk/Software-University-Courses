package ProgrammingFundamentals.BasicSyntaxLab;
import java.util.Scanner;
public class P04BackIn30Minutes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int initHours = Integer.parseInt(scanner.nextLine());
        int initMinutes = Integer.parseInt(scanner.nextLine());

        int totalMinutes =(initHours*60)+initMinutes+30;
        int hours = totalMinutes/60;
        int minutes = totalMinutes % 60;
        if (hours > 23) {
            hours = 0;
        }
        System.out.printf("%d:%02d", hours, minutes);

    }
}
