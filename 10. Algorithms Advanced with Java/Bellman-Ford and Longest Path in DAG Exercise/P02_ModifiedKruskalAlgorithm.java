package L05_LongestPathInDAG_Exercise;

import java.util.*;

public class P02_ModifiedKruskalAlgorithm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nodeCount = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
        int edgeCount = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < edgeCount; i++) {
            int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
            Edge edge = new Edge(tokens[0], tokens[1], tokens[2]);
            edges.add(edge);
        }
        Collections.sort(edges);

        DisjointSet ds = new DisjointSet(nodeCount);
        int mstWeight = 0;
        List<Edge> mst = new ArrayList<>();

        for (Edge edge : edges) {
            int root1 = ds.find(edge.source);
            int root2 = ds.find(edge.destination);

            if (root1 != root2) {
                mst.add(edge);
                ds.union(root1, root2);
                mstWeight += edge.weight;
            }
        }

        System.out.println("Minimum spanning forest weight: " + mstWeight);
//        for (Edge edge : mst) {
//            System.out.printf("(%d %d) -> %d%n", edge.source, edge.destination, edge.weight);
//        }
    }

    public static class DisjointSet {
        public Map<Integer, Integer> parent;

        public DisjointSet(int n) {
            this.parent = new HashMap<>();
            for (int i = 0; i < n; i++) {
                this.parent.put(i, i);
            }
        }

        public int find(int k) {
            if (this.parent.get(k) == k) {
                return k;
            }
            int root = find(this.parent.get(k));
            this.parent.put(k, root);
            return root;
        }

        public void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            this.parent.put(rootB, rootA);
        }
    }

    public static class Edge implements Comparable<Edge> {
        public int source;
        public int destination;
        public int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
