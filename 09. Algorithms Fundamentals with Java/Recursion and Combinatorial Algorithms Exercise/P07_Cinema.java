package L04_Recursion_and_Combinatorics_Exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class P07_Cinema {
    private static String[] friends;
    private static boolean[] used;
    private static String[] arrangement;
    private static Map<Integer, String> fixedSeats;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        friends = scanner.nextLine().split(", ");
        used = new boolean[friends.length];
        arrangement = new String[friends.length];
        fixedSeats = new HashMap<>();
        String line = scanner.nextLine();

        while (!line.equals("generate")) {
            String[] tokens = line.split(" - ");
            String name = tokens[0];
            int index = Integer.parseInt(tokens[1]) - 1;
            fixedSeats.put(index, name);
            for (int i = 0; i < friends.length; i++) {
                if (friends[i].equals(name)) {
                    used[i] = true;
                    break;
                }
            }

            line = scanner.nextLine();
        }

        permute(0);
    }

    private static void permute(int index) {
        if (index >= friends.length) {
            System.out.println(String.join(" ", arrangement));
            return;
        }

        if (fixedSeats.containsKey(index)) {
            arrangement[index] = fixedSeats.get(index);
            permute(index + 1);
        } else {
            for (int i = 0; i < arrangement.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    arrangement[index] = friends[i];
                    permute(index + 1);
                    used[i] = false;
                }
            }
        }
    }
}
