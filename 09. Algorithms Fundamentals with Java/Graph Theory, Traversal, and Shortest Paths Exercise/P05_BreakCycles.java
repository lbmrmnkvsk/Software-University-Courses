package L07_GraphTheoryExercise;
import java.util.*;
public class P05_BreakCycles {
    private static Map<String, TreeSet<String>> graph = new TreeMap<>();
    private static List<String> edgesToRemove = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        // Reading the graph from the console
        while (scanner.hasNextLine() && !(input = scanner.nextLine()).equals("*")) {
            if (input.isEmpty()) {
                continue; // Skip empty lines
            }
            String[] parts = input.split(" -> ");
            if (parts.length < 2) {
                continue; // Skip invalid lines
            }
            String[] connections = parts[1].split(" ");
            graph.putIfAbsent(parts[0], new TreeSet<>());
            for (String connection : connections) {
                graph.get(parts[0]).add(connection);
            }
        }

        // Process edges in alphabetical order
        for (String node : graph.keySet()) {
            TreeSet<String> connections = new TreeSet<>(graph.get(node));
            for (String child : connections) {
                // Temporarily remove the edge
                graph.get(node).remove(child);
                graph.get(child).remove(node);

                if (pathExists(node, child, new HashSet<>())) {
                    // If removing this edge breaks a cycle, add it to the list of edges to remove
                    edgesToRemove.add(node + " - " + child);
                } else {
                    // If no cycle is broken, add the edge back
                    graph.get(node).add(child);
                    graph.get(child).add(node);
                }
            }
        }

        // Output the result
        System.out.println("Edges to remove: " + edgesToRemove.size());
        for (String edge : edgesToRemove) {
            System.out.println(edge);
        }

        scanner.close();
    }

    private static boolean pathExists(String source, String destination, Set<String> visited) {
        if (source.equals(destination)) {
            return true;
        }
        visited.add(source);

        for (String next : graph.get(source)) {
            if (!visited.contains(next) && pathExists(next, destination, visited)) {
                return true;
            }
        }

        visited.remove(source);
        return false;
    }
}
