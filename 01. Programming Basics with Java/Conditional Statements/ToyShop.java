package Z_ProgrammingBasics.ConditionalStatementsExcercise;
import java.util.Scanner;
public class ToyShop {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        double tsenaEkskurziq = Double.parseDouble(scanner.nextLine());
        int broiPazeli = Integer.parseInt(scanner.nextLine());
        int broiKukli = Integer.parseInt(scanner.nextLine());
        int broiMecheta = Integer.parseInt(scanner.nextLine());
        int broiMinioni = Integer.parseInt(scanner.nextLine());
        int broiKamioni = Integer.parseInt(scanner.nextLine());
        double suma = (broiPazeli*2.6)+(broiKukli*3)+(broiMecheta*4.1)+(broiMinioni*8.20)+(broiKamioni*2);
        double obshtBroi = broiPazeli+broiKukli+broiMecheta+broiMinioni+broiKamioni;
        if (obshtBroi >= 50) {
            suma = suma*0.75;
        }
        double pechalba = suma*0.9;

        if (pechalba >= tsenaEkskurziq){
            System.out.printf("Yes! %.2f lv left.", (pechalba-tsenaEkskurziq));
        } else {
            System.out.printf("Not enough money! %.2f lv needed.", (tsenaEkskurziq-pechalba));
        }
    }
}
