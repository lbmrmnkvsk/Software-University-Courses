package L07_GraphTheoryExercise;

import java.util.*;

public class P03_CyclesInAGraph {
    private static Map<String, List<String>> graph = new HashMap<>();
    private static Set<String> visited = new HashSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("End")) {
            String[] tokens = input.split("-");
            String first = tokens[0];
            String second = tokens[1];

            graph.putIfAbsent(first, new ArrayList<>());
            graph.putIfAbsent(second, new ArrayList<>());
            graph.get(first).add(second);
            graph.get(second).add(first);

            input = scanner.nextLine();
        }

        boolean isAcyclic = true;
        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                isAcyclic = dfs(node, null);
                if (!isAcyclic) {
                    break;
                }
            }
        }

        if (isAcyclic) {
            System.out.println("Acyclic: Yes");
        } else {
            System.out.println("Acyclic: No");
        }
    }

    private static boolean dfs(String node, String parent) {
        if (visited.contains(node)) {
            return false;
        }

        visited.add(node);
        for (String child : graph.get(node)) {
            if (!child.equals(parent)) {
                if (!dfs(child, node)) {
                    return false;
                }
            }
        }

        return true;
    }
}
