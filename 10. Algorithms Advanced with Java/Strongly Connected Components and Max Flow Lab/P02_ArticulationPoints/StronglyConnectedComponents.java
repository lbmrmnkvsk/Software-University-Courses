import java.util.*;

public class StronglyConnectedComponents {
    public static List<List<Integer>> sccs;
    public static boolean[] visited;
    public static ArrayDeque<Integer> stack;
    public static List<Integer>[] graph;
    public static List<Integer>[] reverseGraph;

    public static List<List<Integer>> findStronglyConnectedComponents(List<Integer>[] targetGraph) {
        int n = targetGraph.length;
        graph = targetGraph;
        stack = new ArrayDeque<>();
        sccs = new ArrayList<>();
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }

        buildReverseGraph(n);
        Arrays.fill(visited, false);

        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!visited[v]) {
                List<Integer> scc = new ArrayList<>();
                reverseDfs(v, scc);
                sccs.add(scc);
            }
        }

        return sccs;
    }

    private static void reverseDfs(int v, List<Integer> scc) {
        visited[v] = true;
        scc.add(v);
        for (int next : reverseGraph[v]) {
            if (!visited[next]) {
                reverseDfs(next, scc);
            }
        }
    }

    private static void dfs(int v) {
        if (visited[v]) {
            return;
        }

        visited[v] = true;
        for (int child : graph[v]) {
            if (!visited[child]) {
                dfs(child);
            }
        }

        stack.push(v);
    }

    private static void buildReverseGraph(int n) {
        reverseGraph = new List[n];
        for (int i = 0; i < n; i++) {
            reverseGraph[i] = new ArrayList<>();
        }
        for (int v = 0; v < n; v++) {
            for (int w : graph[v]) {
                reverseGraph[w].add(v);
            }
        }
    }
}
