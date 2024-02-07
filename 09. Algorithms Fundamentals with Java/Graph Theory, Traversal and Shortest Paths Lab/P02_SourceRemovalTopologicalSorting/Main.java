import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nodes = Integer.parseInt(scanner.nextLine());
        int edges = Integer.parseInt(scanner.nextLine());

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
            int predecessor = tokens[0];
            int successor = tokens[1];

            graph.putIfAbsent(predecessor, new ArrayList<>());
            List<Integer> currentList = graph.get(predecessor);
            currentList.add(successor);
        }

        int startNode = Integer.parseInt(scanner.nextLine());
        int endNode = Integer.parseInt(scanner.nextLine());

        Map<Integer, Integer> prev = bfs(graph, startNode);
        List<Integer> path = reconstructPath(prev, startNode, endNode);

        if (path == null) {
            System.out.println("No path");
        } else {
            System.out.println("Shortest path length is: " + (path.size() - 1));
            for (int node : path) {
                System.out.print(node + " ");
            }
        }
    }

    private static Map<Integer, Integer> bfs(Map<Integer, List<Integer>> graph, int startNode) {
        Map<Integer, Integer> prev = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> queue = new ArrayDeque<>();

        queue.offer(startNode);
        visited.add(startNode);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            List<Integer> successors = graph.getOrDefault(node, new ArrayList<>());

            for (int next : successors) {
                if (!visited.contains(next)) {
                    queue.offer(next);
                    visited.add(next);
                    prev.put(next, node);
                }
            }
        }

        return prev;
    }

    private static List<Integer> reconstructPath(Map<Integer, Integer> prev, int startNode, int endNode) {
        List<Integer> path = new ArrayList<>();
        Integer at = endNode;

        while (at != null) {
            path.add(at);
            at = prev.get(at);
        }

        Collections.reverse(path);
        if (!path.isEmpty() && path.get(0) == startNode) {
            return path;
        } else {
            return null;
        }
    }


    public static List<Deque<Integer>> getConnectedComponents(List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        List<Deque<Integer>> components = new ArrayList<>();

        for (int start = 0; start < graph.size(); start++) {
            if (!visited[start]) {
                Deque<Integer> component = new ArrayDeque<>();
                dfs(graph, start, visited, component);
                components.add(component);
            }
        }

        return components;
    }

    private static void dfs(List<List<Integer>> graph, int node, boolean[] visited, Deque<Integer> component) {
        if (visited[node]) {
            return;
        } else {
            visited[node] = true;
            component.push(node);
            for (int child : graph.get(node)) {
                dfs(graph, child, visited, component);
            }
        }
    }

    private static void dfs2(List<List<Integer>> graph, int node, boolean[] visited, List<Integer> component) {
        if (visited[node]) {
            return;
        } else {
            visited[node] = true;
            for (int child : graph.get(node)) {
                dfs2(graph, child, visited, component);
            }
            component.add(node);
        }
    }

    public static Collection<String> topSort2(Map<String, List<String>> graph) {
        Map<String, Integer> predecessorCount = getPredecessorCount(graph);
        List<String> sorted = new ArrayList<>();

        while (true) {
            String nodeToRemove = predecessorCount.entrySet().stream()
                    .filter(e -> e.getValue() == 0)
                    .map(e -> e.getKey())
                    .findFirst().orElse(null);

            if (nodeToRemove == null) {
                break;
            }



            for (String child : graph.get(nodeToRemove)) {
                predecessorCount.put(child, predecessorCount.get(child) - 1);
            }

            predecessorCount.remove(nodeToRemove);
            graph.remove(nodeToRemove);
            sorted.add(nodeToRemove);
        }

        if (!graph.isEmpty()) {
            throw new IllegalArgumentException("A cycle was detected in the graph");
        }

        return sorted;
    }

    private static Map<String, Integer> getPredecessorCount(Map<String, List<String>> graph) {
        Map<String, Integer> predecessorCount = new HashMap<>();

        for (Map.Entry<String, List<String>> entry : graph.entrySet()) {
            predecessorCount.putIfAbsent(entry.getKey(), 0);
            for (String child : entry.getValue()) {
                predecessorCount.putIfAbsent(child, 0);
                predecessorCount.put(child, predecessorCount.get(child) + 1);
            }
        }

        return predecessorCount;
    }

    public static Collection<String> topSort(Map<String, List<String>> graph) {
        Set<String> visited = new HashSet<>();
        Set<String> cycles = new HashSet<>();
        List<String> sorted = new ArrayList<>();

        for (String node : graph.keySet()) {
            dfsTopSort(node, graph, visited, cycles, sorted);
        }

        return sorted;
    }
    private static void dfsTopSort(String node, Map<String, List<String>> graph, Set<String> visited, Set<String> cycles, List<String> sorted) {
        if (cycles.contains(node)) {
            throw new IllegalArgumentException();
        }

        if (!visited.contains(node)) {
            visited.add(node);
            cycles.add(node);
            for (String child : graph.get(node)) {
                dfsTopSort(child, graph, visited, cycles, sorted);
            }

            cycles.remove(node);
            sorted.add(0, node);
        }
    }
}
