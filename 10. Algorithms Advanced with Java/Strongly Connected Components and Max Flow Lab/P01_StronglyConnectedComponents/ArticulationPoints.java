import java.util.*;

public class ArticulationPoints {
    private static List<Integer>[] graph;
    private static boolean[] visited;
    private static int[] disc, low;
    private static int[] parent;
    private static boolean[] ap; // Articulation Points
    private static int time;

    public static List<Integer> findArticulationPoints(List<Integer>[] targetGraph) {
        int n = targetGraph.length;
        graph = targetGraph;
        visited = new boolean[n];
        disc = new int[n];
        low = new int[n];
        parent = new int[n];
        ap = new boolean[n];
        time = 0;

        Arrays.fill(parent, -1);

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (ap[i]) {
                result.add(i);
            }
        }
        return result;
    }

    private static void dfs(int u) {
        visited[u] = true;
        disc[u] = low[u] = ++time;
        int children = 0;

        for (int v : graph[u]) {
            if (!visited[v]) {
                parent[v] = u;
                children++;
                dfs(v);
                low[u] = Math.min(low[u], low[v]);

                if (parent[u] == -1 && children > 1) {
                    ap[u] = true;
                }

                if (parent[u] != -1 && low[v] >= disc[u]) {
                    ap[u] = true;
                }
            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

}
