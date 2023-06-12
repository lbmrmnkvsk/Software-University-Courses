package JavaAdvanced.Practice2;

import java.util.*;

public class P01_01_SantasPresentFactory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] materialsArray = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] magicsArray = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        Stack<Integer> materialsStack = new Stack<>();
        ArrayDeque<Integer> magicsQueue = new ArrayDeque<>();

        for (int material : materialsArray) {
            materialsStack.push(material);
        }

        for (int magic : magicsArray) {
            magicsQueue.offer(magic);
        }

        TreeMap<String, Integer> presentsMap = new TreeMap<>();
        presentsMap.put("Doll", 0);
        presentsMap.put("Wooden train", 0);
        presentsMap.put("Teddy bear", 0);
        presentsMap.put("Bicycle", 0);

        while (!materialsStack.isEmpty() && !magicsQueue.isEmpty()) {
            int material = materialsStack.peek();
            int magic = magicsQueue.peek();
            int totalMagic = material * magic;

            if (totalMagic == 150) {
                presentsMap.put("Doll", presentsMap.get("Doll") + 1);
                materialsStack.pop();
                magicsQueue.poll();
            } else if (totalMagic == 250) {
                presentsMap.put("Wooden train", presentsMap.get("Wooden train") + 1);
                materialsStack.pop();
                magicsQueue.poll();
            } else if (totalMagic == 300) {
                presentsMap.put("Teddy bear", presentsMap.get("Teddy bear") + 1);
                materialsStack.pop();
                magicsQueue.poll();
            } else if (totalMagic == 400) {
                presentsMap.put("Bicycle", presentsMap.get("Bicycle") + 1);
                materialsStack.pop();
                magicsQueue.poll();
            } else if (totalMagic < 0) {
                int sum = material + magic;
                materialsStack.pop();
                magicsQueue.poll();
                materialsStack.push(sum);
            } else if (totalMagic > 0) {
                magicsQueue.poll();
                int value = materialsStack.pop();
                materialsStack.push(value + 15);
            } else if (totalMagic == 0) {
                if (material == 0) {
                    materialsStack.pop();
                }

                if (magic == 0) {
                    magicsQueue.poll();
                }
            }
        }

        boolean dollAndTrain = presentsMap.get("Doll") > 0 && presentsMap.get("Wooden train") > 0;
        boolean bearAndBicycle = presentsMap.get("Teddy bear") > 0 && presentsMap.get("Bicycle") > 0;

        if (dollAndTrain || bearAndBicycle) {
            System.out.println("The presents are crafted! Merry Christmas!");
        } else {
            System.out.println("No presents this Christmas!");
        }

        if (!materialsStack.isEmpty()) {
            System.out.print("Materials left: ");
            Collections.reverse(materialsStack);
            System.out.println(materialsStack.toString().replace("[","").replace("]",""));
        }

        if (!magicsQueue.isEmpty()) {
            System.out.print("Magic left: ");
            System.out.println(magicsQueue.toString().replace("[","").replace("]",""));
        }

        for (Map.Entry<String, Integer> entry : presentsMap.entrySet()) {
            if (entry.getValue() > 0) {
                System.out.printf("%s: %d%n", entry.getKey(), entry.getValue());
            }
        }
    }
}
