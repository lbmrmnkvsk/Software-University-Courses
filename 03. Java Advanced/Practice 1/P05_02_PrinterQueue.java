package JavaAdvanced.z_Practice.StacksQueuesLab;
import java.util.ArrayDeque;
import java.util.Scanner;
public class P05_02_PrinterQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();
        ArrayDeque<String> queue = new ArrayDeque<>();

        while (!command.equals("print")) {
            if (command.equals("cancel")) {
                if (queue.isEmpty()) {
                    System.out.println("Printer is on standby");
                } else {
                    String fileName = queue.poll();
                    System.out.printf("Canceled %s%n", fileName);
                }
            } else {
                queue.offer(command);
            }

            command = scanner.nextLine();
        }
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}
