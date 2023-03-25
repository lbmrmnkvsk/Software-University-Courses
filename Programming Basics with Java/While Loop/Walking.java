package Z_ProgrammingBasics.WhileLoopExercise;
import java.util.Scanner;
public class Walking_04 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int totalSteps = 0;
        while (totalSteps < 10000) {
            String input = scanner.nextLine();
            if (input.equals("Going home")) {
                int steps = Integer.parseInt(scanner.nextLine());
                totalSteps += steps;
                break;
            }
            int steps = Integer.parseInt(input);
            totalSteps += steps;
        }
        if (totalSteps >= 10000) {
            System.out.println("Goal reached! Good job!");
            System.out.printf("%d steps over the goal!", (totalSteps - 10000));
        } else {
            System.out.printf("%d more steps to reach goal.", (10000 - totalSteps));
        }
    }
}
