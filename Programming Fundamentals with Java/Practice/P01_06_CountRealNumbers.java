package ProgrammingFundamentals.AssociativeArraysLab;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class P01_06_CountRealNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double[] numbers = Arrays.stream(scanner.nextLine().split("\\s+")).mapToDouble(Double::parseDouble).toArray();
        TreeMap<Double, Integer> count = new TreeMap<>();

        for (double number : numbers) {
            if (!count.containsKey(number)) {
                count.put(number, 1);
            } else {
                int value = count.get(number);
                count.put(number, (value + 1));
            }
        }
        for (Map.Entry<Double, Integer> entry : count.entrySet()) {
            DecimalFormat df = new DecimalFormat("#.######");
            System.out.printf("%s -> %d%n", df.format(entry.getKey()), entry.getValue());
        }
    }
}
