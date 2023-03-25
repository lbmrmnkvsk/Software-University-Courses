package Z_ProgrammingBasics.FirstStepsExercise;
import java.util.Scanner;
public class Repainting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nailon = Integer.parseInt(scanner.nextLine());
        int boq = Integer.parseInt(scanner.nextLine());
        int razreditel = Integer.parseInt(scanner.nextLine());
        int chasove = Integer.parseInt(scanner.nextLine());
        double sumaMateriali = ((nailon+2)*1.50)+(boq*1.1*14.50)+(razreditel*5)+0.40;
        double sumaMaistori = chasove*(sumaMateriali*0.3);
        double krainaSuma = sumaMateriali+sumaMaistori;
        System.out.println(krainaSuma);
    }
}
