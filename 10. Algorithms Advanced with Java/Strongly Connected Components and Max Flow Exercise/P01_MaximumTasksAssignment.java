package L07_SCC_Exercise;
import java.util.*;
public class P01_MaximumTasksAssignment {
    static int[][] residualGraph;
    static List<Integer>[] graph;
    static int[] parent;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int peopleCount = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
        int tasksCount = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);

        int verticesCount = peopleCount + tasksCount + 2;
        graph = new ArrayList[verticesCount];
        for (int i = 0; i < verticesCount; i++) {
            graph[i] = new ArrayList<>();
        }

        residualGraph = new int[verticesCount][verticesCount];
        int source = 0, sink = verticesCount - 1;

        // Connect source to people
        for (int i = 1; i <= peopleCount; i++) {
            residualGraph[source][i] = 1;
            graph[source].add(i);
            graph[i].add(source);
        }

        // Connect tasks to sink
        for (int i = peopleCount + 1; i <= peopleCount + tasksCount; i++) {
            residualGraph[i][sink] = 1;
            graph[i].add(sink);
            graph[sink].add(i);
        }

        // Read the relationships between people and tasks
        for (int i = 1; i <= peopleCount; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < tasksCount; j++) {
                char task = line.charAt(j);
                if (task == 'Y') {
                    int taskNode = 1 + peopleCount + j;
                    residualGraph[i][taskNode] = 1;
                    graph[i].add(taskNode);
                    graph[taskNode].add(i);
                }
            }
        }

        maxFlow(source, sink, verticesCount);

        // After finding the max flow, find the actual assignments
        for (int person = 1; person <= peopleCount; person++) {
            for (int task = peopleCount + 1; task <= peopleCount + tasksCount; task++) {
                // If there's a flow from person to task, then the task is assigned to the person
                if (residualGraph[task][person] == 1) {
                    // Print the assignment (personIndex, taskIndex)
                    System.out.printf("%c-%d\n", 'A' + person - 1, task - peopleCount);
                }
            }
        }
    }

    private static boolean bfs(int source, int sink) {
        boolean[] visited = new boolean[parent.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        Arrays.fill(parent, -1);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v : graph[u]) {
                if (!visited[v] && residualGraph[u][v] > 0) {
                    parent[v] = u;
                    visited[v] = true;
                    queue.add(v);
                }
            }
        }

        return visited[sink];
    }

    private static int maxFlow(int source, int sink, int n) {
        parent = new int[n];
        int maxFlow = 0;

        while (bfs(source, sink)) {
            int pathFlow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residualGraph[u][v]);
            }

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                residualGraph[u][v] -= pathFlow;
                residualGraph[v][u] += pathFlow;
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }
}
