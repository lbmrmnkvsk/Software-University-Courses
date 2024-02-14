import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalAlgorithm {

    public static List<Edge> kruskal(int numberOfVertices, List<Edge> edges) {
        Collections.sort(edges);
        int[] parents = new int[numberOfVertices];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        List<Edge> result = new ArrayList<>();

        for (Edge edge : edges) {
            int rootStart = findRoot(edge.getStartNode(), parents);
            int rootEnd = findRoot(edge.getEndNode(), parents);

            if (rootStart != rootEnd) {
                result.add(edge);
                parents[rootStart] = rootEnd;
            }
        }

        return result;
    }

    public static int findRoot(int node, int[] parents) {
        while (node != parents[node]) {
            node = parents[node];
        }

        return node;
    }
}
