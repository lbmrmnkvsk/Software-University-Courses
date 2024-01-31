package Z_ProgrammingBasics.AdvancedConditionalStatementsLab;
import java.util.Scanner;
public class AnimalType_03 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String animal = scanner.nextLine();
        String message = "";
        switch (animal) {
            case "dog":
                message = "mammal";
                break;
            case "crocodile":
            case "tortoise":
            case "snake":
                message = "reptile";
                break;
            default:
                message = "unknown";
                break;
        }
        System.out.println(message);
    }
}
