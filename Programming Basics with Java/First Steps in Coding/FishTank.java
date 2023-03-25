package Z_ProgrammingBasics.FirstStepsExercise;
import java.util.Scanner;
public class FishTank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int daljina = Integer.parseInt(scanner.nextLine());
        int shirochina = Integer.parseInt(scanner.nextLine());
        int visochina = Integer.parseInt(scanner.nextLine());
        double procent = Double.parseDouble(scanner.nextLine());
        double obemLitri = daljina*shirochina*visochina*0.001;
        double nujniLitri = obemLitri*(1-(procent/100.0));
        System.out.println(nujniLitri);
    }
}
