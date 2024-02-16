package L05_LongestPathInDAG_Exercise;

import java.util.*;

public class P04_CheapTownTour {

    public static class Edge {
        public int source;
        public int destination;
        public int cost;

        public Edge(int source, int destination, int cost) {
            this.source = source;
            this.destination = destination;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nodeCount = Integer.parseInt(scanner.nextLine());
        int edgeCount = Integer.parseInt(scanner.nextLine());
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < edgeCount; i++) {
            int[] tokens = Arrays.stream(scanner.nextLine().split(" - "))
                    .mapToInt(Integer::parseInt).toArray();
            Edge edge = new Edge(tokens[0], tokens[1], tokens[2]);
            edges.add(edge);
        }

        System.out.println("Total cost: " + primsAlgorithm(nodeCount, edges));
    }

    private static int primsAlgorithm(int nodeCount, List<Edge> edges) {
        int totalCost = 0;
        boolean[] visited = new boolean[nodeCount];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));

        visited[0] = true;
        edges.stream().filter(e -> e.source == 0 || e.destination == 0).forEach(pq::offer);

        while (!pq.isEmpty()) {
            Edge currentEdge = pq.poll();
            int nextNode;
            if (visited[currentEdge.source]) {
                nextNode = currentEdge.destination;
            } else {
                nextNode = currentEdge.source;
            }

            if (!visited[nextNode]) {
                visited[nextNode] = true;
                totalCost += currentEdge.cost;
                edges.stream().filter(e -> e.source == nextNode || e.destination == nextNode).forEach(pq::offer);
            }
        }

        for (boolean bool : visited) {
            if (!bool) {
                return  -1;
            }
        }

        return totalCost;
    }
}
