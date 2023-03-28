package ProgrammingFundamentals.FinalExamPrep;
import java.util.*;

public class P03_02_ThePianist {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        LinkedHashMap<String, List<String>> pieces = new LinkedHashMap<>();

        for (int i = 1; i <= n; i++) {
            String[] tokens = scanner.nextLine().split("\\|");
            String piece = tokens[0];
            String composer = tokens[1];
            String key = tokens[2];

            if (!pieces.containsKey(piece)) {
                pieces.put(piece, new ArrayList<>());
                pieces.get(piece).add(0, composer);
                pieces.get(piece).add(1, key);
            }
        }
        String command = scanner.nextLine();

        while (!command.equals("Stop")) {
            String[] tokens = command.split("\\|");

            if (command.startsWith("Add")) {
                String piece = tokens[1];
                String composer = tokens[2];
                String key = tokens[3];
                if (!pieces.containsKey(piece)) {
                    pieces.put(piece, new ArrayList<>());
                    pieces.get(piece).add(0, composer);
                    pieces.get(piece).add(1, key);
                    System.out.printf("%s by %s in %s added to the collection!%n", piece, composer, key);
                } else {
                    System.out.printf("%s is already in the collection!%n", piece);
                }
            } else if (command.startsWith("Remove")) {
                String piece = tokens[1];
                if (pieces.containsKey(piece)) {
                    pieces.remove(piece);
                    System.out.printf("Successfully removed %s!%n", piece);
                } else {
                    System.out.printf("Invalid operation! %s does not exist in the collection.%n", piece);
                }
            } else if (command.startsWith("ChangeKey")) {
                String piece = tokens[1];
                String newKey = tokens[2];
                if (pieces.containsKey(piece)) {
                    pieces.get(piece).set(1, newKey);
                    System.out.printf("Changed the key of %s to %s!%n", piece, newKey);
                } else {
                    System.out.printf("Invalid operation! %s does not exist in the collection.%n", piece);
                }
            }
            command = scanner.nextLine();
        }
        for (Map.Entry<String, List<String>> entry : pieces.entrySet()) {
            System.out.printf("%s -> Composer: %s, Key: %s%n",
                                entry.getKey(), entry.getValue().get(0), entry.getValue().get(1));
        }
    }
}
