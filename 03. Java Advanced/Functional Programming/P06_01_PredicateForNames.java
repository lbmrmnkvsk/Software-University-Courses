package JavaAdvanced.z_Practice.FunctionalProgrammingExercise;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;

public class P06_01_PredicateForNames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        String[] names = scanner.nextLine().split("\\s+");

        Predicate<String> checkValidLength = name -> name.length() <= n;
        Arrays.stream(names).filter(checkValidLength).forEach(name -> System.out.println(name));
    }
}
