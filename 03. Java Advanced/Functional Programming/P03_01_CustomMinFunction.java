package JavaAdvanced.z_Practice.FunctionalProgrammingExercise;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class P03_01_CustomMinFunction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        Function<List<Integer>, Integer> getMinNumber = list -> Collections.min(list);
        int minNumber = getMinNumber.apply(numbers);
        System.out.print(minNumber);
    }
}
