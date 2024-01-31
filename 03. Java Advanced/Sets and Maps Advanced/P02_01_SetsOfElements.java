package JavaAdvanced.z_Practice.SetsMapsExercise;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class P02_01_SetsOfElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int n = tokens[0];
        int m = tokens[1];
        Set<Integer> firstSet = new LinkedHashSet<>();
        Set<Integer> secondSet = new LinkedHashSet<>();

        for (int i = 1; i <= n; i++) {
            int currentNumber = Integer.parseInt(scanner.nextLine());
            firstSet.add(currentNumber);
        }

        for (int i = 1; i <= m; i++) {
            int currentNumber = Integer.parseInt(scanner.nextLine());
            secondSet.add(currentNumber);
        }

        firstSet.retainAll(secondSet);

        for (int number : firstSet) {
            System.out.print(number + " ");
        }
    }
}
