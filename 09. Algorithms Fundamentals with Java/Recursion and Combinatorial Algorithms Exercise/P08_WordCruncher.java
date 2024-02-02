package L04_Recursion_and_Combinatorics_Exercise;

import java.util.*;

public class P08_WordCruncher {
    private static List<String> parts = new ArrayList<>();
    private static boolean[] used;
    private static String target;
    private static List<String> combination = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputParts = scanner.nextLine().split(", ");
        target = scanner.nextLine();

        for (String part : inputParts) {
            if (target.contains(part)) {
                parts.add(part);
            }
        }
        used = new boolean[parts.size()];

        permute(0);
    }

    private static void permute(int index) {
        if (index == target.length()) {
            String text = String.join("", combination);
            if (text.equals(target)) {
                System.out.println(String.join(" ", combination));
            }
            return;
        }

        for (int i = 0; i < parts.size(); i++) {
            if (!used[i]) {
                if (target.startsWith(parts.get(i), index)) {
                    used[i] = true;
                    combination.add(parts.get(i));
                    permute(index + parts.get(i).length());

                    used[i] = false;
                    combination.remove(combination.size() - 1);
                }
            }
        }
    }
}
