package JavaAdvanced.SetsMapsLab;

import java.util.*;

public class P07_01_Cities {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        LinkedHashMap<String, LinkedHashMap<String, List<String>>> data = new LinkedHashMap<>();

        for (int i = 1; i <= n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String continent = tokens[0];
            String country = tokens[1];
            String city = tokens[2];

            if (!data.containsKey(continent)) {
                data.put(continent, new LinkedHashMap<>());
            }
            LinkedHashMap<String, List<String>> currentCountries = data.get(continent);

            if (!currentCountries.containsKey(country)) {
                currentCountries.put(country, new ArrayList<>());
            }

            currentCountries.get(country).add(city);
        }

        for (Map.Entry<String, LinkedHashMap<String, List<String>>> entry1 : data.entrySet()) {
            System.out.println(entry1.getKey() + ":");

            LinkedHashMap<String, List<String>> currentCountries = entry1.getValue();
            for (Map.Entry<String, List<String>> entry2 : currentCountries.entrySet()) {
                System.out.printf("  %s -> %s%n", entry2.getKey(), String.join(", ", entry2.getValue()) );
            }
        }
    }
}
