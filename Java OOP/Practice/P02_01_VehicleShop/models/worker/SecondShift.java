package vehicleShop.models.worker;

public class SecondShift extends BaseWorker {
    private static final int STRENGTH = 70;

    public SecondShift(String name) {
        super(name, STRENGTH);
    }

    @Override
    public void working() {
        int currentStrength = this.getStrength();
        currentStrength -= 15;
        if (currentStrength < 0) {
            currentStrength = 0;
        }
        this.setStrength(currentStrength);
    }
}
