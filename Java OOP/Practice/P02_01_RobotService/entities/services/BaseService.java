package robotService.entities.services;

import robotService.common.ConstantMessages;
import robotService.common.ExceptionMessages;
import robotService.entities.robot.Robot;
import robotService.entities.supplements.Supplement;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseService implements Service {
    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Robot> robots;

    public BaseService(String name, int capacity) {
        this.setName(name);
        this.setCapacity(capacity);
        this.supplements = new ArrayList<>();
        this.robots = new ArrayList<>();
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return supplements;
    }

    @Override
    public Collection<Robot> getRobots() {
        return robots;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.SERVICE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int sumHardness() {
        int sum = 0;
        for (Supplement supplement : this.supplements) {
            sum += supplement.getHardness();
        }
        return sum;
    }

    public void addRobot(Robot robot) {
        if (this.robots.size() < this.capacity) {
            this.robots.add(robot);
        } else {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY_FOR_ROBOT);
        }
    }

    public void removeRobot(Robot robot) {
        this.robots.remove(robot);
    }

    public void addSupplement(Supplement supplement) {
        this.supplements.add(supplement);
    }

    public void feeding() {
        for (Robot robot : this.robots) {
            robot.eating();
        }
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s:", this.name, this.getClass().getSimpleName())).append(System.lineSeparator());
        if (this.robots.size() == 0) {
            sb.append("Robots: none").append(System.lineSeparator());
        } else {
            sb.append("Robots: ");
            for (Robot robot : this.robots) {
                sb.append(String.format("%s ", robot.getName()));
            }
            sb.append(System.lineSeparator());
        }
        sb.append(String.format("Supplements: %d Hardness: %d", this.supplements.size(), this.sumHardness()));

        return sb.toString().trim();
    }
}
