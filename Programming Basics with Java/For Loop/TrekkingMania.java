package Z_ProgrammingBasics.ForLoopExercise;
import java.util.Scanner;
public class TrekkingMania_07 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int countGroups = Integer.parseInt(scanner.nextLine());
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        int p4 = 0;
        int p5 = 0;
        for (int i = 1; i <= countGroups; i++) {
            int countMembers = Integer.parseInt(scanner.nextLine());
            if ( countMembers <= 5) {
                p1 += countMembers;
            } else if (countMembers <= 12) {
                p2 += countMembers;
            } else if (countMembers <=25) {
                p3 += countMembers;
            } else if (countMembers <= 40) {
                p4 += countMembers;
            } else if (countMembers >= 41) {
                p5 += countMembers;
            }
        }
        int totalMembers = p1 + p2 + p3 + p4 + p5;
        double p1Percent = ((1.0*p1)/totalMembers)*100;
        double p2Percent = ((1.0*p2)/totalMembers)*100;
        double p3Percent = ((1.0*p3)/totalMembers)*100;
        double p4Percent = ((1.0*p4)/totalMembers)*100;
        double p5Percent = ((1.0*p5)/totalMembers)*100;
        System.out.printf("%.2f%%%n", p1Percent);
        System.out.printf("%.2f%%%n", p2Percent);
        System.out.printf("%.2f%%%n", p3Percent);
        System.out.printf("%.2f%%%n", p4Percent);
        System.out.printf("%.2f%%%n", p5Percent);

    }
}
