package Z_ProgrammingBasics.ConditionalStatementsExcercise;
import java.util.Scanner;
public class WorldSwimmingRecord {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        double rekordSekundi = Double.parseDouble(scanner.nextLine());
        double razstoqnie = Double.parseDouble(scanner.nextLine());
        double sekundi1m = Double.parseDouble(scanner.nextLine());
        double zabavqne = 12.5*Math.floor(razstoqnie/15);
        double vreme = zabavqne + (razstoqnie*sekundi1m);
        if (vreme < rekordSekundi) {
            System.out.printf("Yes, he succeeded! The new world record is %.2f seconds.", vreme);
        } else {
            System.out.printf("No, he failed! He was %.2f seconds slower.", (vreme-rekordSekundi));
        }
    }
}
