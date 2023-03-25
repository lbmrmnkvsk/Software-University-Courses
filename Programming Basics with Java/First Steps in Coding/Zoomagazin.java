package Z_ProgrammingBasics.FirstSteps;

import java.util.Scanner;

public class Zoomagazin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dogPackages = Integer.parseInt(scanner.nextLine());
        int catPackages = Integer.parseInt(scanner.nextLine());
        double cost = (dogPackages*2.5)+(catPackages*4);
        System.out.println(cost+" lv.");
    }
}
