package JavaAdvanced.z_Practice.StacksQueuesExercise;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;
public class P04_01_BasicQueueOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int s = scanner.nextInt();
        int x = scanner.nextInt();
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            queue.offer(scanner.nextInt());
        }

        for (int i = 1; i <=s; i++) {
            queue.poll();
        }

        if (queue.isEmpty()) {
            System.out.println(0);
        } else {
            if (queue.contains(x)) {
                System.out.println("true");
            } else {
                System.out.println(Collections.min(queue));
            }
        }
    }
}
