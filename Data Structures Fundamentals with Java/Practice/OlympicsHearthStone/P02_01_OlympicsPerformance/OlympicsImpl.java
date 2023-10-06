import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OlympicsImpl implements Olympics {
private List<Competitor> competitors;
private List<Competition> competitions;

    public OlympicsImpl() {
        this.competitions = new ArrayList<>();
        this.competitors = new ArrayList<>();
    }

    @Override
    public void addCompetitor(int id, String name) {
        if (this.competitors.stream().anyMatch(c -> c.getId() == id)) {
            throw new IllegalArgumentException();
        }

        Competitor competitor = new Competitor(id, name);
        this.competitors.add(competitor);
    }

    @Override
    public void addCompetition(int id, String name, int score) {
        if (this.competitions.stream().anyMatch(c -> c.getId() == id)) {
            throw new IllegalArgumentException();
        }

        this.competitions.add(new Competition(name, id, score));
    }

    @Override
    public void compete(int competitorId, int competitionId) {
        Competitor competitor = this.competitors.stream().filter(c -> c.getId() == competitorId).findFirst().orElse(null);
        Competition competition = this.competitions.stream().filter(c -> c.getId() == competitionId).findFirst().orElse(null);
        if (competitor == null || competition == null) {
            throw new IllegalArgumentException();
        }

        competition.getCompetitors().add(competitor);
        competitor.setTotalScore(competitor.getTotalScore() + competition.getScore());
    }

    @Override
    public void disqualify(int competitionId, int competitorId) {
        Competition competition = this.competitions.stream().filter(c -> c.getId() == competitionId).findFirst().orElse(null);
        Competitor competitor = this.competitors.stream().filter(c -> c.getId() == competitorId).findFirst().orElse(null);
        if (competitor == null || competition == null || !competition.getCompetitors().contains(competitor)) {
            throw new IllegalArgumentException();
        }

        competition.getCompetitors().remove(competitor);
        competitor.setTotalScore(competitor.getTotalScore() - competition.getScore());
    }

    @Override
    public Iterable<Competitor> findCompetitorsInRange(long min, long max) {
        return this.competitors.stream().filter(c -> c.getTotalScore() > min && c.getTotalScore() <= max)
                .sorted(Comparator.comparing(Competitor::getId))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Competitor> getByName(String name) {
        List<Competitor> result = this.competitors.stream().filter(c -> c.getName().equals(name))
                .sorted(Comparator.comparing(Competitor::getId)).collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public Iterable<Competitor> searchWithNameLength(int minLength, int maxLength) {
        return this.competitors.stream().filter(c -> c.getName().length() >= minLength && c.getName().length() <= maxLength)
                .sorted(Comparator.comparing(Competitor::getId))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean contains(int competitionId, Competitor comp) {
        Competition competition = this.competitions.stream().filter(c -> c.getId() == competitionId).findFirst().orElse(null);
        if (competition == null) {
            throw new IllegalArgumentException();
        }

        return competition.getCompetitors().contains(comp);
    }

    @Override
    public int competitionsCount() {
        return this.competitions.size();
    }

    @Override
    public int competitorsCount() {
        return this.competitors.size();
    }

    @Override
    public Competition getCompetition(int id) {
        Competition competition = this.competitions.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if (competition == null) {
            throw new IllegalArgumentException();
        }

        return competition;
    }
}
