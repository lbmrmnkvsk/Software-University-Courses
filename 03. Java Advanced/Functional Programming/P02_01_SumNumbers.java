package JavaAdvanced.FunctionalProgrammingLab;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class P02_01_SumNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split(", ")).map(Integer::parseInt).collect(Collectors.toList());
        int count = numbers.size();

        Function<List<Integer>, Integer> sumListElements = list -> {
          int sum = 0;
          for (int element : list) {
              sum += element;
          }

          return sum;
        };

        int sum = sumListElements.apply(numbers);

        System.out.printf("Count = %d%n", count);
        System.out.printf("Sum = %d%n", sum);
    }
}
