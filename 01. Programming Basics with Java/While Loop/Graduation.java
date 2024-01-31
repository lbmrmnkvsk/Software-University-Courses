package Z_ProgrammingBasics.WhileLoopLab;
import java.util.Scanner;
public class Graduation_08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        int year = 1;
        int fails = 0;
        double totalGrade = 0;
        while (year <= 12) {
            if (fails > 1) {
                System.out.printf("%s has been excluded at %d grade", name, year);
                break;
            }
            double currentGrade = Double.parseDouble(scanner.nextLine());
            if (currentGrade >= 4) {
                year++;
                totalGrade+= currentGrade;
            } else {
                fails++;
            }
        }
        if (year >= 12) {
            System.out.printf("%s graduated. Average grade: %.2f", name, (totalGrade)/12);
        }
    }
}
