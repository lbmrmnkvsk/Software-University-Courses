package vehicleShop.models.tool;

import vehicleShop.common.ExceptionMessages;

public class ToolImpl implements Tool {
    private int power;

    public ToolImpl(int power) {
        this.setPower(power);
    }

    public void setPower(int power) {
        if (power < 0) {
            throw new IllegalArgumentException(ExceptionMessages.TOOL_POWER_LESS_THAN_ZERO);
        }
        this.power = power;
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public void decreasesPower() {
        int currentPower = this.getPower();
        currentPower -= 5;
        if (currentPower < 0) {
            currentPower = 0;
        }

        this.setPower(currentPower);
    }

    @Override
    public boolean isUnfit() {
        return this.getPower() == 0;
    }
}
