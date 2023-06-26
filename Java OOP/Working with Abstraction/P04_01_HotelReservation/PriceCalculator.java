package JavaOOP.AbstractionLab.P04_01_HotelReservation;

public class PriceCalculator {
    private double pricePerDay;
    private int numberOfDays;
    private Season season;
    private DiscountType discountType;

    public PriceCalculator(double pricePerDay, int numberOfDays, Season season, DiscountType discountType) {
        this.pricePerDay = pricePerDay;
        this.numberOfDays = numberOfDays;
        this.season = season;
        this.discountType = discountType;
    }

    public double calculate() {
        return this.pricePerDay * this.numberOfDays * this.season.getPriceFactor() * this.discountType.getDiscountFactor();
    }
}
