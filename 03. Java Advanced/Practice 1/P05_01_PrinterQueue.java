package JavaAdvanced.z_Practice.StacksQueues;
import java.util.ArrayDeque;
import java.util.Scanner;
public class P05_01_PrinterQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<String> printerQueue = new ArrayDeque<>();
        String input = scanner.nextLine();

        while (!input.equals("print")) {
            if (input.equals("cancel")) {
                if (printerQueue.isEmpty()) {
                    System.out.println("Printer is on standby");
                } else {
                    String firstFile = printerQueue.poll();
                    System.out.printf("Canceled %s%n", firstFile);
                }
            } else {
                printerQueue.offer(input);
            }

            input = scanner.nextLine();
        }
        while (!printerQueue.isEmpty()) {
            String printedFile = printerQueue.poll();
            System.out.println(printedFile);
        }
    }
}
