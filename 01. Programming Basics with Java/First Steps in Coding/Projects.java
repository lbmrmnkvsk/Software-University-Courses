package Z_ProgrammingBasics.FirstSteps;

import java.util.Scanner;

public class Projects {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        int numProjects = Integer.parseInt(scanner.nextLine());
        int neededHours = numProjects*3;
        System.out.printf("The architect %s will need %d hours to complete %d project/s.",
                name, neededHours, numProjects);
    }
}
