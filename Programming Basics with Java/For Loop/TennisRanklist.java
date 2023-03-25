package Z_ProgrammingBasics.ForLoopExercise;
import java.util.Scanner;
public class TennisRanklist_08 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int countTournaments = Integer.parseInt(scanner.nextLine());
        int initialPoints = Integer.parseInt(scanner.nextLine());
        double totalPoints = initialPoints;
        int countW = 0;
        for (int i = 1; i <= countTournaments; i++){
            String resultTournament = scanner.nextLine();
            switch (resultTournament) {
                case "W":
                    totalPoints += 2000;
                    countW++;
                    break;
                case "F":
                    totalPoints += 1200;
                    break;
                case "SF":
                    totalPoints += 720;
                    break;
            }
        }
        System.out.printf("Final points: %.0f%n" , totalPoints);
        System.out.printf("Average points: %.0f%n", Math.floor((totalPoints-initialPoints)/countTournaments));
        System.out.printf("%.2f%%", ((1.0*countW)/countTournaments)*100);
    }
}
