package JavaOOP.EncapsulationExercise.P05_01_FootballTeamGenerator;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LinkedHashMap<String, Team> teams = new LinkedHashMap<>();
        String line = scanner.nextLine();

        while (!line.equals("END")) {
            String[] tokens = line.split(";");
            String command = tokens[0];
            String teamName = tokens[1];

            if (command.equals("Team")) {
                Team team = new Team(teamName);
                teams.put(teamName, team);
            } else if (command.equals("Add")) {
                if (!teams.containsKey(teamName)) {
                    System.out.printf("Team %s does not exist.%n", teamName);
                } else {
                    String playerName = tokens[2];
                    int endurance = Integer.parseInt(tokens[3]);
                    int sprint = Integer.parseInt(tokens[4]);
                    int dribble = Integer.parseInt(tokens[5]);
                    int passing = Integer.parseInt(tokens[6]);
                    int shooting = Integer.parseInt(tokens[7]);
                    Player player = new Player(playerName, endurance, sprint, dribble, passing, shooting);
                    Team team = teams.get(teamName);
                    team.addPlayer(player);
                }
            } else if (command.equals("Remove")) {
                String playerName = tokens[2];
                Team team = teams.get(teamName);
                team.removePlayer(playerName);
            } else if (command.equals("Rating")) {
                if (!teams.containsKey(teamName)) {
                    System.out.printf("Team %s does not exist.%n", teamName);
                } else {
                    Team team = teams.get(teamName);
                    System.out.printf("%s - %.0f%n", team.getName(), team.getRating());
                }
            }

            line = scanner.nextLine();
        }
    }
}
