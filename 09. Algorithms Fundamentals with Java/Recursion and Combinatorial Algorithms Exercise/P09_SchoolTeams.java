package L04_Recursion_and_Combinatorics_Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P09_SchoolTeams {

    private static String[] girls;
    private static String[] boys;
    private static String[] girlCombination = new String[3];
    private static String[] boyCombination = new String[2];
    private static List<String> girlTeams = new ArrayList<>();
    private static List<String> boyTeams = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        girls = scanner.nextLine().split(", ");
        boys = scanner.nextLine().split(", ");

        combineGirls(0, 0);
        combineBoys(0, 0);

        for (String girlTeam : girlTeams) {
            for (String boyTeam : boyTeams) {
                System.out.println(girlTeam + ", " + boyTeam);
            }
        }
    }

    private static void combineGirls(int index, int start) {
        if (index == girlCombination.length) {
            girlTeams.add(String.join(", ", girlCombination));
            return;
        }

        for (int i = start; i < girls.length; i++) {
            girlCombination[index] = girls[i];
            combineGirls(index + 1, i + 1);
        }
    }

    private static void combineBoys(int index, int start) {
        if (index == boyCombination.length) {
            boyTeams.add(String.join(", ", boyCombination));
            return;
        }

        for (int i = start; i < boys.length; i++) {
            boyCombination[index] = boys[i];
            combineBoys(index + 1, i + 1);
        }
    }
}
