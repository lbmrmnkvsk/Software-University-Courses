package L04_LongestPathInDAG;

import java.util.*;

public class P02_LongestPath {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countNodes = Integer.parseInt(scanner.nextLine());
        int countEdges = Integer.parseInt(scanner.nextLine());
        Map<Integer, List<Edge>> graph = new HashMap<>();

        for (int i = 0; i < countEdges; i++) {
            int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
            Edge edge = new Edge(tokens[0], tokens[1], tokens[2]);
            graph.putIfAbsent(tokens[0], new ArrayList<>());
            graph.get(tokens[0]).add(edge);
        }
        int s = Integer.parseInt(scanner.nextLine());
        int d = Integer.parseInt(scanner.nextLine());

        longestPath(graph, s, d, countNodes);
    }

    private static void longestPath(Map<Integer, List<Edge>> graph, int s, int d, int countNodes) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] dist = new int[countNodes + 1];
        boolean[] visited = new boolean[countNodes + 1];

        for (int i = 1; i <= countNodes; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack, graph);
            }
        }

        Arrays.fill(dist, Integer.MIN_VALUE);
        dist[s] = 0;

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (dist[node] != Integer.MIN_VALUE) {
                List<Edge> edges = graph.get(node);
                if (edges != null) {
                    for (Edge edge : edges) {
                        if (dist[edge.destination] < dist[node] + edge.weight) {
                            dist[edge.destination] = dist[node] + edge.weight;
                        }
                    }
                }
            }
        }

        if (dist[d] == Integer.MIN_VALUE) {
            System.out.println("No path found");
        } else {
            System.out.println(dist[d]);
        }
    }

    private static void topologicalSortUtil(int node, boolean[] visited, Deque<Integer> stack, Map<Integer, List<Edge>> graph) {
        visited[node] = true;
        List<Edge> edges = graph.get(node);
        if (edges != null) {
            for (Edge edge : edges) {
                if (!visited[edge.destination]) {
                    topologicalSortUtil(edge.destination, visited, stack, graph);
                }
            }
        }

        stack.push(node);
    }

    public static class Edge {
        public int source;
        public int destination;
        public int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
}
