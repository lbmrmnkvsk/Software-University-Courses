package L05_LongestPathInDAG_Exercise;

import java.util.*;

public class P03_MostReliablePath {

    public static class Edge {
        public int node;
        public double reliability;

        public Edge(int node, double reliability) {
            this.node = node;
            this.reliability = reliability;
        }
    }

    public static class Node implements Comparable<Node> {
        public int vertex;
        public double reliability;

        public Node(int vertex, double reliability) {
            this.vertex = vertex;
            this.reliability = reliability;
        }

        @Override
        public int compareTo(Node other) {
            return Double.compare(other.reliability, this.reliability);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nodes = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
        String[] pathParts = scanner.nextLine().split("\\s+");
        int start = Integer.parseInt(pathParts[1]);
        int end = Integer.parseInt(pathParts[3]);
        int edges = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
            int from = tokens[0];
            int to = tokens[1];
            double reliability = (tokens[2]*1.00) / 100;
            graph.get(from).add(new Edge(to, reliability));
            graph.get(to).add(new Edge(from, reliability));
        }

        double[] maxReliability = new double[nodes];
        int[] prev = new int[nodes];
        Arrays.fill(maxReliability, 0);
        Arrays.fill(prev, -1);
        maxReliability[start] = 1;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 1));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.vertex == end) {
                System.out.printf("Most reliable path reliability: %.2f%%%n", maxReliability[end] * 100);
                List<Integer> path = new ArrayList<>();
                int at = end;

                while (at != -1) {
                    path.add(at);
                    at = prev[at];
                }

                Collections.reverse(path);
                for (int i = 0; i <= path.size() - 2; i++) {
                    System.out.print(path.get(i) + " -> ");
                }
                System.out.println(path.get(path.size() - 1));

                return;
            }

            for (Edge edge : graph.get(current.vertex)) {
                double newReliability = maxReliability[current.vertex] * edge.reliability;
                if (maxReliability[edge.node] < newReliability) {
                    maxReliability[edge.node] = newReliability;
                    prev[edge.node] = current.vertex;
                    pq.offer(new Node(edge.node, newReliability));
                }
            }
        }

        System.out.println("No path found");
    }
}
