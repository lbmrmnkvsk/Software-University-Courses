package JavaAdvanced.z_Practice.FunctionalProgrammingLab;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P01_01_SortEvenNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split(", ")).map(Integer::parseInt).collect(Collectors.toList());
        numbers.removeIf(num -> num % 2 != 0);
        printWithSeparator(numbers);

        List<Integer> sortedNumbers = numbers
                .stream()
                .sorted()
                .collect(Collectors.toList());
        printWithSeparator(sortedNumbers);
    }

    private static void printWithSeparator(List<Integer> list) {
        for (int i = 0; i <= list.size() - 1; i++) {
            if (i < list.size() - 1) {
                System.out.print(list.get(i) + ", ");
            } else {
                System.out.print(list.get(i));
            }
        }
        System.out.println();
    }
}
