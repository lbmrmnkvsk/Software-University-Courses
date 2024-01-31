package Z_ProgrammingBasics.NestedLoops;
import java.util.Scanner;
public class EasterCompetition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cakes = Integer.parseInt(scanner.nextLine());
        int bestResult = Integer.MIN_VALUE;
        String bestBaker = "";
        for (int i = 1; i <= cakes; i++) {
            String baker = scanner.nextLine();
            String input = scanner.nextLine();
            int result = 0;
            while (!input.equals("Stop")) {
                result += Integer.parseInt(input);
                input = scanner.nextLine();
            }
            System.out.printf("%s has %d points.%n", baker, result);
            if (result > bestResult) {
                bestResult = result;
                bestBaker = baker;
                System.out.printf("%s is the new number 1!%n", baker);
            }
        }
        System.out.printf("%s won competition with %d points!", bestBaker, bestResult);
    }
}
