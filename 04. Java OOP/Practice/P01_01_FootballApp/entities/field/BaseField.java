package football.entities.field;

import football.common.ConstantMessages;
import football.common.ExceptionMessages;
import football.entities.player.Player;
import football.entities.supplement.Supplement;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseField implements Field {
    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Player> players;

    public BaseField(String name, int capacity) {
        this.setName(name);
        this.setCapacity(capacity);
        this.supplements = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.FIELD_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int sumEnergy() {
        int sum = 0;
        for (Supplement supplement : this.supplements) {
            sum += supplement.getEnergy();
        }

        return sum;
    }

    @Override
    public void addPlayer(Player player) {
        if (this.players.size() < this.capacity) {
            this.players.add(player);
        } else {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY);
        }
    }

    @Override
    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        this.supplements.add(supplement);
    }

    @Override
    public void drag() {
        for (Player player : this.players) {
            player.stimulation();
        }
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s (%s):", this.getName(), this.getClass().getSimpleName())).append(System.lineSeparator());

        if (this.players.size() < 1) {
            sb.append("Player: none").append(System.lineSeparator());
        } else {
            sb.append("Player: ");
            for (Player player : this.players) {
                sb.append(String.format("%s ", player.getName()));
            }
            sb.append(System.lineSeparator());
        }

        sb.append(String.format("Supplement: %d", this.supplements.size())).append(System.lineSeparator());
        sb.append(String.format("Energy: %d", this.sumEnergy()));

        return sb.toString().trim();
    }

    @Override
    public Collection<Player> getPlayers() {
        return this.players;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return this.supplements;
    }
}
