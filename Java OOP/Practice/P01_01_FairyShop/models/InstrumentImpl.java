package fairyShop.models;

import fairyShop.common.ExceptionMessages;

public class InstrumentImpl implements Instrument {
    private int power;

    public InstrumentImpl(int power) {
        this.setPower(power);
    }


    @Override
    public int getPower() {
        return this.power;
    }

    public void setPower(int power) {
        if (power < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INSTRUMENT_POWER_LESS_THAN_ZERO);
        }
        this.power = power;
    }

    @Override
    public void use() {
        int decreasedPower = this.power - 10;
        if (decreasedPower < 0) {
            decreasedPower = 0;
        }

        this.power = decreasedPower;
    }

    @Override
    public boolean isBroken() {
        return this.power <= 0;
    }
}
