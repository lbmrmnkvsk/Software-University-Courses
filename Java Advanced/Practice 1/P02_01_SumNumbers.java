package JavaAdvanced.z_Practice.FunctionalProgrammingLab;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P02_01_SumNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split(", ")).map(Integer::parseInt).collect(Collectors.toList());
        int sum = numbers
                .stream()
                .mapToInt(a -> a)
                .sum();

        System.out.println("Count = " + numbers.size());
        System.out.print("Sum = " + sum);
    }
}
