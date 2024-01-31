package JavaAdvanced.Practice2;

import java.util.*;

public class P01_02_RubberDuckDebuggers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] programmersArray = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] tasksArray = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        ArrayDeque<Integer> programmersQueue = new ArrayDeque<>();
        Stack<Integer> tasksStack = new Stack<>();

        for (int programmer : programmersArray) {
            programmersQueue.offer(programmer);
        }
        for (int task : tasksArray) {
            tasksStack.push(task);
        }

        LinkedHashMap<String, Integer> ducksMap = new LinkedHashMap<>();
        ducksMap.put("Darth Vader Ducky", 0);
        ducksMap.put("Thor Ducky", 0);
        ducksMap.put("Big Blue Rubber Ducky", 0);
        ducksMap.put("Small Yellow Rubber Ducky", 0);

        while (!programmersQueue.isEmpty() && !tasksStack.isEmpty()) {
            int programmer = programmersQueue.peek();
            int task = tasksStack.peek();
            int time = task * programmer;

            if (time >= 0 && time <= 60) {
                ducksMap.put("Darth Vader Ducky", ducksMap.get("Darth Vader Ducky") + 1);
                programmersQueue.poll();
                tasksStack.pop();
            } else if (time >= 61 && time <= 120) {
                ducksMap.put("Thor Ducky", ducksMap.get("Thor Ducky") + 1);
                programmersQueue.poll();
                tasksStack.pop();
            } else if (time >= 121 && time <= 180) {
                ducksMap.put("Big Blue Rubber Ducky", ducksMap.get("Big Blue Rubber Ducky") + 1);
                programmersQueue.poll();
                tasksStack.pop();
            } else if (time >= 181 && time <= 240) {
                ducksMap.put("Small Yellow Rubber Ducky", ducksMap.get("Small Yellow Rubber Ducky") + 1);
                programmersQueue.poll();
                tasksStack.pop();
            } else if (time >= 241) {
                int value = tasksStack.pop();
                tasksStack.push(value - 2);
                int value2 = programmersQueue.poll();
                programmersQueue.offer(value2);
            }
        }

        System.out.println("Congratulations, all tasks have been completed! Rubber ducks rewarded:");
        for (Map.Entry<String, Integer> entry : ducksMap.entrySet()) {
            System.out.printf("%s: %d%n", entry.getKey(), entry.getValue());
        }
    }
}
