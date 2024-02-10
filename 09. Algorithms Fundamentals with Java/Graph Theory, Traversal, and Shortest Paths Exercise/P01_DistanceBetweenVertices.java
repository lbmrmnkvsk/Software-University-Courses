package L07_GraphTheoryExercise;

import java.util.*;

public class P01_DistanceBetweenVertices {
    private static Map<Integer, List<Integer>> graph = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int edges = Integer.parseInt(scanner.nextLine());
        int pairs = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < edges; i++) {
            String[] tokens = scanner.nextLine().split(":");
            int predecessor = Integer.parseInt(tokens[0]);
            graph.putIfAbsent(predecessor, new ArrayList<>());

            if (tokens.length > 1 && !tokens[1].trim().isEmpty()) {
                int[] successors = Arrays.stream(tokens[1].split("\\s+"))
                        .mapToInt(Integer::parseInt).toArray();
                for (int successor : successors) {
                    graph.get(predecessor).add(successor);
                }
            }
        }

        for (int i = 0; i < pairs; i++) {
            int[] pair = Arrays.stream(scanner.nextLine().split("-")).mapToInt(Integer::parseInt).toArray();
            System.out.printf("{%d, %d} -> %d%n", pair[0], pair[1], bfs(pair[0], pair[1]));
        }
    }

    private static int bfs(int start, int end) {
        if (start == end) {
            return 0;
        }

        Deque<Integer> queue = new ArrayDeque<>();
        Map<Integer, Integer> visited = new HashMap<>();
        queue.offer(start);
        visited.put(start, 0);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            List<Integer> successors = graph.getOrDefault(current, new ArrayList<>());

            for (int successor : successors) {
                if (!visited.containsKey(successor)) {
                    queue.offer(successor);
                    visited.put(successor, visited.get(current) + 1);
                    if (successor == end) {
                        return visited.get(successor);
                    }
                }
            }
        }

        return -1;
    }
}
