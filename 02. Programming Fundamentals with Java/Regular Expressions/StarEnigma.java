package ProgrammingFundamentals.RegularExpressionsExercise;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P04_01_StarEnigma {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        String regex = "@(?<planet>[A-Za-z]+)[^@!:>\\-]*?:(?<population>[0-9]+)[^@!:>\\-]*?!(?<attackType>[AD])![^@!:>\\-]*?->(?<soldierCount>[0-9]+)";
        Pattern pattern = Pattern.compile(regex);
        List<String> attackedPlanets = new ArrayList<>();
        List<String> destroyedPlanets = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            String encryptedMessage = scanner.nextLine();
            String decryptedMessage = getDecryptedMessage(encryptedMessage);
            Matcher matcher = pattern.matcher(decryptedMessage);

            if (matcher.find()) {
                String planet = matcher.group("planet");
                String attackType = matcher.group("attackType");
                if (attackType.equals("A")) {
                    attackedPlanets.add(planet);
                } else if (attackType.equals("D")) {
                    destroyedPlanets.add(planet);
                }
            }
        }

        Collections.sort(attackedPlanets);
        Collections.sort(destroyedPlanets);

        System.out.println("Attacked planets: " + attackedPlanets.size());
        for (String planet : attackedPlanets) {
            System.out.printf("-> %s%n", planet);
        }

        System.out.println("Destroyed planets: " + destroyedPlanets.size());
        for (String planet : destroyedPlanets) {
            System.out.printf("-> %s%n", planet);
        }
    }

    private static String getDecryptedMessage(String message) {
        int count = 0;

        for (char symbol : message.toCharArray()) {
            if (symbol == 's' || symbol == 't' || symbol == 'a' || symbol == 'r' ||
                symbol == 'S' || symbol == 'T' || symbol == 'A' || symbol == 'R') {
                count++;
            }
        }

        StringBuilder decryptedMessage = new StringBuilder();

        for (char symbol : message.toCharArray()) {
            char decryptedSymbol = (char) (symbol - count);
            decryptedMessage.append(decryptedSymbol);
        }

        return decryptedMessage.toString();
    }
}
