import java.util.*;

public class Dijkstra {

    public static class Node implements Comparable<Node> {
        public int vertex;
        public int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static List<Integer> dijkstraAlgorithm(int[][] graph, int sourceNode, int destinationNode) {
        int[] dist = new int[graph.length];
        boolean[] visited = new boolean[graph.length];
        int[] prev = new int[graph.length];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[sourceNode] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(sourceNode, dist[sourceNode]));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int vertex = node.vertex;
            if (visited[vertex]) {
                continue;
            }

            visited[vertex] = true;
            for (int v = 0; v < graph.length; v++) {
                int edgeDistance = graph[vertex][v];
                if (edgeDistance > 0 && !visited[v] && dist[vertex] + edgeDistance < dist[v]) {
                    dist[v] = dist[vertex] + edgeDistance;
                    prev[v] = vertex;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        if (dist[destinationNode] == Integer.MAX_VALUE || prev[destinationNode] == -1) {
            return null;
        }

        List<Integer> path = new ArrayList<>();
        int at = destinationNode;
        while (at != -1) {
            path.add(at);
            at = prev[at];
        }

        Collections.reverse(path);
        return path;
    }
}
