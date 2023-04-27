package JavaAdvanced.z_Practice.FunctionalProgrammingExercise;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class P04_02_AppliedArithmetic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        String command = scanner.nextLine();
        Function<List<Integer>, List<Integer>> addCommand = list -> list.stream().map(number -> number += 1).collect(Collectors.toList());
        Function<List<Integer>, List<Integer>> multiplyCommand = list -> list.stream().map(number -> number *= 2).collect(Collectors.toList());
        Function<List<Integer>, List<Integer>> subtractCommand = list -> list.stream().map(number -> number -= 1).collect(Collectors.toList());
        Consumer<List<Integer>> printCommand = list -> list.forEach(number -> System.out.print(number + " "));

        while (!command.equals("end")) {
            if (command.equals("add")) {
                numbers = addCommand.apply(numbers);
            } else if (command.equals("multiply")) {
                numbers = multiplyCommand.apply(numbers);
            } else if (command.equals("subtract")) {
                numbers = subtractCommand.apply(numbers);
            } else if (command.equals("print")) {
                printCommand.accept(numbers);
                System.out.println();
            }

            command = scanner.nextLine();
        }
    }
}
