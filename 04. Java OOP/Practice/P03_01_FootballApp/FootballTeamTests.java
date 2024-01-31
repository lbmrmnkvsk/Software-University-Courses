package football;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FootballTeamTests {
    private FootballTeam team;

    @Before
    public void createATeam() {
        this.team = new FootballTeam("Test", 1);
    }

    @Test
    public void constructorCreatesACorrectTeam() {
        Assert.assertEquals("Test", this.team.getName());
        Assert.assertEquals(1, this.team.getVacantPositions());
        Assert.assertEquals(0, this.team.getCount());
    }

    @Test (expected = NullPointerException.class)
    public void setNameMethodThrowsExceptionWhenProvidedNameIsNull() {
        new FootballTeam(null, 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setVacantPositionsMethodThrowsExceptionWhenGivenNumberIsNegative() {
        new FootballTeam("Test", -10);
    }

    @Test
    public void addFootballerMethodAddsCorrectFootballer() {
        Footballer footballer = new Footballer("Test");
        this.team.addFootballer(footballer);
        Assert.assertEquals(1, this.team.getCount());
    }

    @Test (expected = IllegalArgumentException.class)
    public void addFootballerMethodThrowsExceptionWhenTheTeamIsFull() {
        Footballer footballer = new Footballer("Test");
        this.team.addFootballer(footballer);
        this.team.addFootballer(footballer);
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeFootballerMethodThrowsExceptionWhenGivePlayerIsNotOnTheTeam() {
        this.team.removeFootballer("Ivan");
    }

    @Test
    public void removeFootballerMethodRemovesTheCorrectPlayer() {
        Footballer footballer = new Footballer("Test");
        this.team.addFootballer(footballer);
        Assert.assertEquals(1, this.team.getCount());
        this.team.removeFootballer("Test");
        Assert.assertEquals(0, this.team.getCount());
    }

    @Test (expected = IllegalArgumentException.class)
    public void footballerForSaleMethodThrowsExceptionWhenGivenPlayerIsNotOnTheTeam() {
        this.team.footballerForSale("Ivan");
    }

    @Test
    public void footballerForSaleMethodReturnsTheCorrectFootballer() {
        Footballer footballer = new Footballer("Test");
        this.team.addFootballer(footballer);
        Footballer result = this.team.footballerForSale("Test");
        Assert.assertEquals(footballer, result);
        Assert.assertFalse(result.isActive());
    }

    @Test
    public void getStatisticsMethodReturnsTheCorrectResult() {
        Footballer footballer = new Footballer("Test");
        this.team.addFootballer(footballer);
        String expected = "The footballer Test is in the team Test.";
        String actual = this.team.getStatistics();
        Assert.assertEquals(expected, actual);
    }
}
