package ProgrammingFundamentals.AssociativeArraysLab;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class P01_01_CountRealNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double[] numbersArray = Arrays.stream(scanner.nextLine().split("\\s+")).mapToDouble(Double::parseDouble).toArray();

        TreeMap<Double, Integer> countMap = new TreeMap<>();

        for (double number : numbersArray) {
            if (!countMap.containsKey(number)) {
                countMap.put(number, 1);
            } else {
                int value = countMap.get(number);
                countMap.put(number, (value + 1));
            }
        }

        for (Map.Entry<Double, Integer> entry : countMap.entrySet()) {
            DecimalFormat df = new DecimalFormat("#.######");
            double num = entry.getKey();

            System.out.printf("%s -> %d%n", df.format(num), entry.getValue());
        }
    }
}
