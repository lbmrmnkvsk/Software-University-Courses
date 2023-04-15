package JavaAdvanced.z_Practice.SetsMapsLab;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
public class P04_01_CountRealNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double[] input = Arrays.stream(scanner.nextLine().split("\\s+")).mapToDouble(Double::parseDouble).toArray();
        Map<Double, Integer> occurrences = new LinkedHashMap<>();

        for (double number : input) {
            if (!occurrences.containsKey(number)) {
                occurrences.put(number, 1);
            } else {
                int value = occurrences.get(number);
                occurrences.put(number, (value + 1) );
            }
        }

        for (Map.Entry<Double, Integer> entry : occurrences.entrySet()) {
            System.out.printf("%.1f -> %d%n", entry.getKey(), entry.getValue());
        }
    }
}
