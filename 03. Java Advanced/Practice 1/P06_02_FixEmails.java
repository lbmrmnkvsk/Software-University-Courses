package JavaAdvanced.z_Practice.SetsMapsExercise;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
public class P06_02_FixEmails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, String> emails = new LinkedHashMap<>();
        String command = scanner.nextLine();

        while (!command.equals("stop")) {
            String name = command;
            String email = scanner.nextLine();
            String emailLC = email.toLowerCase();

            if (!emailLC.endsWith("us") && !emailLC.endsWith("uk") && !emailLC.endsWith("com")) {
                emails.put(name, email);
            }

            command = scanner.nextLine();
        }

        for (Map.Entry<String, String> entry : emails.entrySet()) {
            System.out.printf("%s -> %s%n", entry.getKey(), entry.getValue());
        }
    }
}
