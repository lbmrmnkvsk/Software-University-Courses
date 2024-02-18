package L07_SCC_Exercise;
import java.util.*;
public class P02_FindBiconnectedComponents {private static int time;
    private static List<List<Integer>> bcc; // Bi-connected components
    private static Stack<Integer> stack;
    private static int[] disc, low;
    private static boolean[] visited;
    private static Set<Integer> articulationPoints;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of nodes and edges, ignoring the text labels
        int nodes = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
        int edges = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);

        // Initialize graph
        List<Integer>[] graph = new List[nodes];
        for (int i = 0; i < nodes; ++i) {
            graph[i] = new ArrayList<>();
        }

        // Read the edges and populate the graph
        for (int i = 0; i < edges; ++i) {
            String[] edgeInput = scanner.nextLine().split("\\s+");
            int from = Integer.parseInt(edgeInput[0]);
            int to = Integer.parseInt(edgeInput[1]);
            graph[from].add(to);
            graph[to].add(from);
        }

        findBiConnectedComponents(graph, nodes);

        // Print the number of bi-connected components
        System.out.println("Number of bi-connected components: " + bcc.size());
//        for (List<Integer> component : bcc) {
//            component.sort(Comparator.naturalOrder());
//            System.out.println(component.size());
//            for (int node : component) {
//                System.out.print(node + " ");
//            }
//            System.out.println();
//        }
    }

    private static void findBiConnectedComponents(List<Integer>[] graph, int N) {
        disc = new int[N];
        low = new int[N];
        visited = new boolean[N];
        stack = new Stack<>();
        bcc = new ArrayList<>();
        articulationPoints = new HashSet<>();
        time = 0;

        Arrays.fill(disc, -1); // Initialize discovery times to -1 for unvisited nodes

        for (int i = 0; i < N; ++i) {
            if (!visited[i]) {
                dfs(i, -1, graph);
                // Pop off remaining stack items if any to form the last BCC
                List<Integer> component = new ArrayList<>();
                while (!stack.isEmpty()) {
                    component.add(stack.pop());
                }
                if (!component.isEmpty()) {
                    bcc.add(component);
                }
            }
        }
    }

    private static void dfs(int u, int parent, List<Integer>[] graph) {
        visited[u] = true;
        disc[u] = low[u] = ++time;
        int children = 0;

        for (int v : graph[u]) {
            if (!visited[v]) {
                stack.push(v);
                children++;
                dfs(v, u, graph);
                low[u] = Math.min(low[u], low[v]);

                // If u is an articulation point, extract the stack to a new component
                if ((parent == -1 && children > 1) || (parent != -1 && low[v] >= disc[u])) {
                    if (parent != -1) {
                        articulationPoints.add(u);
                    }
                    List<Integer> component = new ArrayList<>();
                    int w;
                    do {
                        w = stack.pop();
                        component.add(w);
                    } while (w != v);
                    bcc.add(component);
                }
            } else if (v != parent && disc[v] < disc[u]) {
                low[u] = Math.min(low[u], disc[v]);
                stack.push(v);
            }
        }
    }
}
