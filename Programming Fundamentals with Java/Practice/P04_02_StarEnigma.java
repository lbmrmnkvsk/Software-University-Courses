package ProgrammingFundamentals.RegularExpressionsExercise;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P04_02_StarEnigma {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        List<String> attackedPlanets = new ArrayList<>();
        List<String> destroyedPlanets = new ArrayList<>();
        String regex = "@(?<planet>[A-Za-z]+)[^-@!:>]*?:(?<population>[0-9]+)[^-@!:>]*?!(?<attackType>[AD])![^-@!:>]*?->(?<soldierCount>[0-9]+)";
        Pattern pattern = Pattern.compile(regex);

        for (int i = 1; i <= n; i++) {
            String encryptedMessage = scanner.nextLine();
            String message = decryptMessage(encryptedMessage);

            Matcher matcher = pattern.matcher(message);
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

        System.out.printf("Attacked planets: %d%n", attackedPlanets.size());
        for (String planet : attackedPlanets) {
            System.out.printf("-> %s%n", planet);
        }
        System.out.printf("Destroyed planets: %d%n", destroyedPlanets.size());
        for (String planet : destroyedPlanets) {
            System.out.printf("-> %s%n", planet);
        }
    }

    private static String decryptMessage(String encryptedMessage) {
        int countSTAR = 0;
        for (char symbol : encryptedMessage.toCharArray()) {
            if (symbol == 'S' || symbol == 'T' || symbol == 'A' || symbol == 'R' ||
                symbol == 's' || symbol == 't' || symbol == 'a' || symbol == 'r') {
                countSTAR++;
            }
        }

        StringBuilder decryptedSb = new StringBuilder();

        for (char encryptedSymbol : encryptedMessage.toCharArray()) {
            char decryptedSymbol = (char) (encryptedSymbol - countSTAR);
            decryptedSb.append(decryptedSymbol);
        }

        return decryptedSb.toString();
    }
}
