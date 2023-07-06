package JavaOOP.InterfacesLab.P02_01_CarShopExtended;

public class Seat extends CarImpl implements Sellable {
    private Double price;

    public Seat(String model, String color, Integer horsePower, String countryProduced, Double price) {
        super(model, color, horsePower, countryProduced);
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("This is %s produced in %s and have %d tires", super.getModel(), super.countryProduced(), TIRES)).append(System.lineSeparator());
        sb.append(String.format("%s sells for %f", super.getModel(), this.getPrice()));
        return sb.toString();
    }

    @Override
    public Double getPrice() {
        return this.price;
    }
}
