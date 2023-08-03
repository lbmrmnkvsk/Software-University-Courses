package fairyShop.models;

public class Sleepy extends BaseHelper {
    public Sleepy(String name) {
        super(name, 50);
    }

    @Override
    public void work() {
        int decreasedEnergy = this.getEnergy() - 15;
        if (decreasedEnergy < 0) {
            decreasedEnergy = 0;
        }

        this.setEnergy(decreasedEnergy);
    }
}
