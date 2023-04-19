package JavaAdvanced.z_Practice.SetsMapsExercise;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class P08_02_UserLogs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TreeMap<String, LinkedHashMap<String, Integer>> data = new TreeMap<>();
        String command = scanner.nextLine();

        while (!command.equals("end")) {
            String[] tokens = command.split("\\s+");
            // "IP={IP.Address} message={A&sample&message} user={username}"
            String ip = tokens[0].split("=")[1];
            String username = tokens[2].split("=")[1];

            if (!data.containsKey(username)) {
                data.put(username, new LinkedHashMap<>());
            }

            Map<String, Integer> currentIps = data.get(username);

            if (!currentIps.containsKey(ip)) {
                currentIps.put(ip, 1);
            } else {
                int value = currentIps.get(ip);
                currentIps.put(ip, (value + 1) );
            }

            command = scanner.nextLine();
        }

        for (Map.Entry<String, LinkedHashMap<String, Integer>> entry1 : data.entrySet()) {
            System.out.printf("%s:%n", entry1.getKey());
            Map<String, Integer> currentIps = entry1.getValue();

            int counter = 1;
            int size = currentIps.size();
            for (Map.Entry<String, Integer> entry2 : currentIps.entrySet()) {
                if (counter < size) {
                    System.out.printf("%s => %d, ", entry2.getKey(), entry2.getValue());
                } else {
                    System.out.printf("%s => %d.%n", entry2.getKey(), entry2.getValue());
                }
                counter++;
            }
        }
    }
}
