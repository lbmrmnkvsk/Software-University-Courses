package JavaOOP.EncapsulationExercise.P05_01_FootballTeamGenerator;

public class Player {
private String name;
private int endurance;
private int sprint;
private int dribble;
private int passing;
private int shooting;

    public Player(String name, int endurance, int sprint, int dribble, int passing, int shooting) {
       this.setName(name);
       this.setEndurance(endurance);
       this.setSprint(sprint);
       this.setDribble(dribble);
       this.setPassing(passing);
       this.setShooting(shooting);
    }

    public String getName() {
        return this.name;
    }

    public double overallSkillLevel() {
        return (this.endurance + this.sprint + this.dribble + this.passing + this.shooting) / 5.0;
    }

    private void setName(String name) {
        if (name.trim().length() >= 1) {
            this.name = name;
        } else {
            System.out.println("A name should not be empty.");
        }
    }

    private void setEndurance(int endurance) {
        if (endurance >= 0 && endurance <= 100) {
            this.endurance = endurance;
        } else {
            System.out.println("Endurance should be between 0 and 100.");
        }
    }

    private void setSprint(int sprint) {
        if (sprint >= 0 && sprint <= 100) {
            this.sprint = sprint;
        } else {
            System.out.println("Sprint should be between 0 and 100.");
        }
    }

    private void setDribble(int dribble) {
        if (dribble >= 0 && dribble <= 100) {
            this.dribble = dribble;
        } else {
            System.out.println("Dribble should be between 0 and 100.");
        }
    }

    private void setPassing(int passing) {
        if (passing >= 0 && passing <= 100) {
            this.passing = passing;
        } else {
            System.out.println("Passing should be between 0 and 100.");
        }
    }

    private void setShooting(int shooting) {
        if (shooting >= 0 && shooting <= 100) {
            this.shooting = shooting;
        } else {
            System.out.println("Passing should be between 0 and 100.");
        }
    }
}
