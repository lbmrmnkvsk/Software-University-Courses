package JavaAdvanced.z_Practice.FunctionalProgrammingExercise;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class P07_01_FindTheSmallestElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        Function<List<Integer>, Integer> getIndexOfMinNumber = list -> list.lastIndexOf(Collections.min(list));

        int index = getIndexOfMinNumber.apply(numbers);
        System.out.print(index);
    }
}
