package JavaAdvanced.z_Practice.SetsMapsExercise;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class P01_01_UniqueUsernames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<String> usernames = new LinkedHashSet<>();
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= n; i++) {
            String username = scanner.nextLine();
            usernames.add(username);
        }

        for (String username : usernames) {
            System.out.println(username);
        }
    }
}
