package Z_ProgrammingBasics.FirstStepsExercise;
import java.util.Scanner;
public class BasketballEquipment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int fee = Integer.parseInt(scanner.nextLine());
        double kecove = 0.6*fee;
        double ekip = 0.8*kecove;
        double topka = 0.25*ekip;
        double aksesoari = 0.2*topka;
        double total = fee + kecove + ekip + topka + aksesoari;
        System.out.println(total);
    }
}
