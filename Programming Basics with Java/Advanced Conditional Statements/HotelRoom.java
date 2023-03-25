package Z_ProgrammingBasics.AdvancedConditionalStatementsExercise;
import java.util.Scanner;
public class HotelRoom_07 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String month = scanner.nextLine();
        int num = Integer.parseInt(scanner.nextLine());
        double priceStudio =0;
        double priceApartment =0;
        switch (month) {
            case "May":
            case "October":
                priceStudio = 50*num;
                priceApartment = 65*num;
                if (num > 7 && num <=14) {
                    priceStudio = priceStudio*0.95;
                } else if (num >14) {
                    priceStudio = priceStudio*0.7;
                    priceApartment = priceApartment*0.9;
                }
                break;
            case "June":
            case "September":
                priceStudio = 75.20*num;
                priceApartment = 68.70*num;
                if (num >14) {
                    priceStudio = priceStudio*0.8;
                    priceApartment = priceApartment*0.9;
                }
                break;
            case "July":
            case "August":
                priceStudio = 76*num;
                priceApartment = 77*num;
                if (num > 14) {
                    priceApartment = priceApartment*0.9;
                }
                break;
        }
        System.out.printf("Apartment: %.2f lv.%n", priceApartment);
        System.out.printf("Studio: %.2f lv.", priceStudio);
    }
}
