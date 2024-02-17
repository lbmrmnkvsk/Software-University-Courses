package L05_LongestPathInDAG_Exercise;

import java.util.*;

public class P06_BigTrip {
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nodeCount = Integer.parseInt(scanner.nextLine());
        int edgeCount = Integer.parseInt(scanner.nextLine());
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < edgeCount; i++) {
            int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
            Edge edge = new Edge(tokens[0] - 1, tokens[1] - 1, tokens[2]);
            edges.add(edge);
        }

        int start = Integer.parseInt(scanner.nextLine()) - 1;
        int end = Integer.parseInt(scanner.nextLine()) - 1;
        int[] dist = new int[nodeCount];
        int[] prev = new int[nodeCount];
        Arrays.fill(dist, Integer.MIN_VALUE);
        Arrays.fill(prev, -1);
        dist[start] = 0;

        for (int i = 0; i < nodeCount - 1; i++) {
            for (Edge edge : edges) {
                if (dist[edge.source] != Integer.MIN_VALUE
                && dist[edge.destination] < dist[edge.source] + edge.weight) {
                    dist[edge.destination] = dist[edge.source] + edge.weight;
                    prev[edge.destination] = edge.source;
                }
            }
        }

        for (Edge edge : edges) {
            if (dist[edge.source] != Integer.MIN_VALUE
            && dist[edge.destination] < dist[edge.source] + edge.weight) {
                System.out.println("Positive cycle detected.");
                return;
            }
        }

        if (dist[end] == Integer.MIN_VALUE) {
            System.out.println("No path found.");
        } else {
            System.out.println(dist[end]);
            List<Integer> path = new ArrayList<>();
            int at = end;

            while (at != -1) {
                path.add(at);
                at = prev[at];
            }

            Collections.reverse(path);
            for (int node : path) {
                System.out.print((node + 1) + " ");
            }
        }
    }
}
