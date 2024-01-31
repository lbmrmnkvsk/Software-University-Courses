package JavaOOP.EncapsulationExercise.P05_01_FootballTeamGenerator;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
        this.name = name;
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(String playerName) {
        boolean wasRemoved = false;
        Player playerToRemove = null;
        for (Player player : this.players) {
            if (player.getName().equals(playerName)) {
                playerToRemove = player;
                wasRemoved = true;
            }
        }
        if (wasRemoved) {
            this.players.remove(playerToRemove);
        } else {
            System.out.printf("Player %s is not in %s team.%n", playerName, this.getName());
        }
    }

    public double getRating() {
        double sum = 0;
        for (Player player : this.players) {
            sum += player.overallSkillLevel();
        }
        if (this.players.isEmpty()) {
            return 0;
        } else {
            return sum / this.players.size();
        }
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name.trim().length() >= 1) {
            this.name = name;
        } else {
            System.out.println("A name should not be empty.");
        }
    }
}
