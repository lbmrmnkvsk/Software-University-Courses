package L07_GraphTheoryExercise;
import java.util.*;
public class P06_RoadReconstruction {
    private static Map<Integer, List<Integer>> graph = new HashMap<>();
    private static List<String> importantStreets = new ArrayList<>();
    private static Map<Integer, Integer> discoveryTimes = new HashMap<>();
    private static Map<Integer, Integer> lowTimes = new HashMap<>();
    private static Set<Integer> visited = new HashSet<>();
    private static int time = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int buildings = Integer.parseInt(scanner.nextLine());
        int streets = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < streets; i++) {
            int[] road = Arrays.stream(scanner.nextLine().split(" - "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            graph.putIfAbsent(road[0], new ArrayList<>());
            graph.putIfAbsent(road[1], new ArrayList<>());

            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }

        for (int i = 1; i <= buildings; i++) {
            if (!visited.contains(i)) {
                findBridges(i, -1);
            }
        }

        System.out.println("Important streets:");
        importantStreets.forEach(System.out::println);

        scanner.close();
    }

    private static void findBridges(int node, int parent) {
        visited.add(node);
        discoveryTimes.put(node, time);
        lowTimes.put(node, time);
        time++;

        for (int child : graph.getOrDefault(node, Collections.emptyList())) {
            if (child == parent) {
                continue;
            }
            if (!visited.contains(child)) {
                findBridges(child, node);
                lowTimes.put(node, Math.min(lowTimes.get(node), lowTimes.get(child)));
                if (discoveryTimes.get(node) < lowTimes.get(child)) {
                    importantStreets.add(Math.min(node, child) + " " + Math.max(node, child));
                }
            } else {
                lowTimes.put(node, Math.min(lowTimes.get(node), discoveryTimes.get(child)));
            }
        }
    }
}
