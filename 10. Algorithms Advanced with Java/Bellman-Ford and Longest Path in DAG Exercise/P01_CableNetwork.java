package L05_LongestPathInDAG_Exercise;

import java.util.*;

public class P01_CableNetwork {

    public static class Connection {
        public int node;
        public int cost;

        public Connection(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int budget = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
        int nodesCount = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
        int edgesCount = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
        Map<Integer, List<Connection>> graph = new HashMap<>();
        Set<Integer> connectedNodes = new HashSet<>();
        PriorityQueue<Connection> availableConnections = new PriorityQueue<>(Comparator.comparingInt(c -> c.cost));

        for (int i = 0; i < edgesCount; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            int from = Integer.parseInt(tokens[0]);
            int to = Integer.parseInt(tokens[1]);
            int cost = Integer.parseInt(tokens[2]);
            boolean isConnected = tokens.length == 4 && tokens[3].equals("connected");

            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(new Connection(to, cost));

            graph.putIfAbsent(to, new ArrayList<>());
            graph.get(to).add(new Connection(from, cost));

            if (isConnected) {
                connectedNodes.add(to);
                connectedNodes.add(from);
            }
        }

        for (int node : connectedNodes) {
            for (Connection connection : graph.get(node)) {
                if (!connectedNodes.contains(connection.node)) {
                    availableConnections.offer(connection);
                }
            }
        }

        int usedBudget = 0;
        while (!availableConnections.isEmpty() && usedBudget < budget) {
            Connection cheapestConnection = availableConnections.poll();
            if (connectedNodes.contains(cheapestConnection.node)) {
                continue;
            }
            if (cheapestConnection.cost + usedBudget > budget) {
                break;
            }

            usedBudget += cheapestConnection.cost;
            connectedNodes.add(cheapestConnection.node);

            for (Connection connection : graph.get(cheapestConnection.node)) {
                if (!connectedNodes.contains(connection.node)) {
                    availableConnections.offer(connection);
                }
            }
        }

        System.out.println("Budget used: " + usedBudget);
    }
}
