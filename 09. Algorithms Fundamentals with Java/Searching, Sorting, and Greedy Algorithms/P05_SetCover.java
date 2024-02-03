package L05_Searching_Sorting;
import java.util.*;
import java.util.stream.Collectors;
public class P05_SetCover {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<Integer> universe = Arrays.stream(scanner.nextLine().substring(10).split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());

        int numberOfSets = Integer.parseInt(scanner.nextLine().split(": ")[1]);
        List<Set<Integer>> sets = new ArrayList<>();
        for (int i = 0; i < numberOfSets; i++) {
            sets.add(Arrays.stream(scanner.nextLine().split(", "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toSet()));
        }

        List<Set<Integer>> selectedSets = chooseSets(sets, universe);

        System.out.println("Sets to take:");
        for (Set<Integer> set : selectedSets) {
            System.out.println(set);
        }
    }

    private static List<Set<Integer>> chooseSets(List<Set<Integer>> sets, Set<Integer> universe) {
        List<Set<Integer>> selectedSets = new ArrayList<>();
        Set<Integer> universeCopy = new HashSet<>(universe);

        while (!universeCopy.isEmpty()) {
            Set<Integer> chosenSet = sets.stream()
                    .max(Comparator.comparingInt(s -> intersectionSize(s, universeCopy)))
                    .orElse(null);

            if (chosenSet == null) {
                break;
            }

            selectedSets.add(chosenSet);
            universeCopy.removeAll(chosenSet);
        }

        return selectedSets;
    }

    private static int intersectionSize(Set<Integer> set, Set<Integer> universe) {
        return (int) set.stream().filter(universe::contains).count();
    }
}
