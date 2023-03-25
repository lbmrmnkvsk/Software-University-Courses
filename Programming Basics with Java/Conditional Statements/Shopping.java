package Z_ProgrammingBasics.ConditionalStatementsExcercise;
import java.util.Scanner;
public class Shopping_07 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        double budget = Double.parseDouble(scanner.nextLine());
        int broiVideokarti = Integer.parseInt(scanner.nextLine());
        int broiProcesori = Integer.parseInt(scanner.nextLine());
        int broiRam = Integer.parseInt(scanner.nextLine());
        double obshtoGPU = 250*broiVideokarti;
        double cenaCPU = 0.35*obshtoGPU;
        double cenaRam = 0.1*obshtoGPU;
        double cena = obshtoGPU+(cenaCPU*broiProcesori)+(cenaRam*broiRam);
        if (broiVideokarti > broiProcesori) {
            cena=cena*0.85;
        }
        if (budget >= cena) {
            System.out.printf("You have %.2f leva left!", (budget-cena));
        } else {
            System.out.printf("Not enough money! You need %.2f leva more!", (cena-budget));
        }
    }
}
