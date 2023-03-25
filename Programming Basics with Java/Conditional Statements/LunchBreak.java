package Z_ProgrammingBasics.ConditionalStatementsExcercise;
import java.util.Scanner;
public class LunchBreak_08 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String imeSerial = scanner.nextLine();
        double prodaljitelnostEpizod = Double.parseDouble(scanner.nextLine());
        double prodaljitelnostPochivka = Double.parseDouble(scanner.nextLine());
        double prodaljitelnostObqd = prodaljitelnostPochivka/8;
        double prodaljitelnostOtdih = prodaljitelnostPochivka/4;
        double ostanaloVreme = (prodaljitelnostPochivka - prodaljitelnostObqd - prodaljitelnostOtdih);
        if (ostanaloVreme >= prodaljitelnostEpizod) {
            System.out.printf("You have enough time to watch %s and left with %.0f minutes free time.",
                    imeSerial, Math.ceil(ostanaloVreme-prodaljitelnostEpizod));
        } else {
            System.out.printf("You don't have enough time to watch %s, you need %.0f more minutes.",
                    imeSerial, Math.ceil(prodaljitelnostEpizod-ostanaloVreme));
        }
    }
}
