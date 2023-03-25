package Z_ProgrammingBasics.ForLoopExercise;
import java.util.Scanner;
public class Oscars_06 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String actorName = scanner.nextLine();
        double academyPoints = Double.parseDouble(scanner.nextLine());
        int countExaminers = Integer.parseInt(scanner.nextLine());
        double sumAllPoints = academyPoints;
        for (int i = 1; i <= countExaminers; i++) {
            String examinerName = scanner.nextLine();
            double examinerPoints = Double.parseDouble(scanner.nextLine());
            double currentPoints = examinerName.length()*examinerPoints/2;
            sumAllPoints += currentPoints;
            if (sumAllPoints >= 1250.5){
                System.out.printf("Congratulations, %s got a nominee for leading role with %.1f!",
                        actorName, sumAllPoints);
                break;
            }
        }
        if (sumAllPoints < 1250.5) {
            System.out.printf("Sorry, %s you need %.1f more!",
                    actorName, (1250.5-sumAllPoints));
        }
    }
}
