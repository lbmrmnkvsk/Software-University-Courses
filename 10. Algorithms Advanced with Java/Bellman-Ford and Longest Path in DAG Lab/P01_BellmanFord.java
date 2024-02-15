package L04_LongestPathInDAG;

import java.util.*;

public class P01_BellmanFord {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int V = Integer.parseInt(scanner.nextLine());
        int E = Integer.parseInt(scanner.nextLine());
        Edge[] edges = new Edge[E];

        for (int i = 0; i < E; i++) {
            int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
            Edge edge = new Edge(tokens[0] - 1, tokens[1] - 1, tokens[2]);
            edges[i] = edge;
        }

        int source = Integer.parseInt(scanner.nextLine()) - 1;
        int destination = Integer.parseInt(scanner.nextLine()) - 1;

        bellmanFord(edges, V, source, destination);
    }

    private static void bellmanFord(Edge[] edges, int V, int source, int destination) {
        int[] dist = new int[V];
        int[] prev = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[source] = 0;

        for (int i = 0; i < V - 1; i++) {
            for (Edge edge : edges) {
                if (dist[edge.source] != Integer.MAX_VALUE && dist[edge.source] + edge.weight < dist[edge.destination]) {
                    dist[edge.destination] = dist[edge.source] + edge.weight;
                    prev[edge.destination] = edge.source;
                }
            }
        }

        for (Edge edge : edges) {
            if (dist[edge.source] != Integer.MAX_VALUE && dist[edge.source] + edge.weight < dist[edge.destination]) {
                System.out.println("Negative Cycle Detected");
                return;
            }
        }

        if (dist[destination] == Integer.MAX_VALUE) {
            System.out.println("No path found");
        } else {
            List<Integer> path = new ArrayList<>();
            int at = destination;

            while (at != -1) {
                path.add(at);
                at = prev[at];
            }
            Collections.reverse(path);

            for (int v : path) {
                System.out.print((v + 1) + " ");
            }
            System.out.println();
            System.out.println(dist[destination]);
        }
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
