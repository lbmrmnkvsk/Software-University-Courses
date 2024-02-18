import java.util.*;

public class EdmondsKarp {
    public static int findMaxFlow(int[][] capacity) {
        int n = capacity.length;
        int[][] flow = new int[n][n];
        int[] parent = new int[n];
        int maxFlow = 0;

        while (true) {
            Arrays.fill(parent, -1);
            parent[0] = -2; // Mark the source vertex as visited
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(0); // Start BFS from the source
            int[] minCapacity = new int[n];
            minCapacity[0] = Integer.MAX_VALUE;

            // BFS to find the shortest augmenting path
            while (!queue.isEmpty()) {
                int u = queue.poll();
                for (int v = 0; v < n; v++) {
                    if (u == v) continue;
                    int cap = capacity[u][v] - flow[u][v];
                    if (cap > 0 && parent[v] == -1) {
                        parent[v] = u;
                        minCapacity[v] = Math.min(minCapacity[u], cap);
                        if (v == n - 1) { // Reached sink
                            maxFlow += minCapacity[n - 1];
                            // Augment flow along the path
                            int cur = n - 1;
                            while (cur != 0) {
                                int prev = parent[cur];
                                flow[prev][cur] += minCapacity[n - 1];
                                flow[cur][prev] -= minCapacity[n - 1];
                                cur = prev;
                            }
                            break;
                        }
                        queue.offer(v);
                    }
                }
            }
            if (parent[n - 1] == -1) { // No augmenting path found
                break;
            }
        }

        return maxFlow;
    }
}
