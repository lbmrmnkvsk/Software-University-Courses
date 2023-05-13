package JavaAdvanced.StacksQueuesLab;

import java.util.ArrayDeque;
import java.util.Scanner;

public class _05_01_PrinterQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<String> printerQueue = new ArrayDeque<>();
        String command = scanner.nextLine();

        while (!command.equals("print")) {
            if (command.equals("cancel")) {
                if (printerQueue.isEmpty()) {
                    System.out.println("Printer is on standby");
                } else {
                    String canceledFile = printerQueue.poll();
                    System.out.println("Canceled " + canceledFile);
                }
            } else {
                printerQueue.offer(command);
            }

            command = scanner.nextLine();
        }

        while (!printerQueue.isEmpty()) {
            System.out.println(printerQueue.poll());
        }
    }
}
