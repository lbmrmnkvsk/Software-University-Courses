package bank;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BankTests {
    private Bank bank;

    @Before
    public void createAnEmptyBank() {
        this.bank = new Bank("Test", 1);
    }

    @Test
    public void constructorCreatesCorrectBank() {
        Assert.assertEquals("Test", this.bank.getName());
        Assert.assertEquals(1, this.bank.getCapacity());
        Assert.assertEquals(0, this.bank.getCount());
    }

    @Test (expected = NullPointerException.class)
    public void setNameMethodThrowsExceptionWhenNameIsNull() {
        new Bank(null, 1);
    }

    @Test (expected = NullPointerException.class)
    public void setNameMethodThrowsExceptionWhenNameIsWhitespace() {
        new Bank("  ", 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setCapacityMethodThrowsExceptionWhenCapacityIsNegative() {
        new Bank("Test", -10);
    }

    @Test
    public void addClientMethodAddsCorrectClient() {
        this.bank.addClient(new Client("Test"));
        Assert.assertEquals(1, this.bank.getCount());
    }

    @Test (expected = IllegalArgumentException.class)
    public void addClientMethodThrowsExceptionWhenBankIsFull() {
        this.bank.addClient(new Client("Test"));
        this.bank.addClient(new Client("Test"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeClientMethodThrowsExceptionWhenGivenClientIsNotInTheBank() {
        this.bank.removeClient("Ivan");
    }

    @Test
    public void removeClientMethodRemovesCorrectClient() {
        this.bank.addClient(new Client("Test"));
        this.bank.removeClient("Test");
        Assert.assertEquals(0, this.bank.getCount());
    }

    @Test (expected = IllegalArgumentException.class)
    public void loanWithdrawalMethodThrowsExceptionWhenGivenNameIsNotInTheBank() {
        this.bank.loanWithdrawal("Ivan");
    }

    @Test
    public void loanWithdrawalMethodReturnsTheCorrectClient() {
        this.bank.addClient(new Client("Test"));
        Client client = this.bank.loanWithdrawal("Test");
        Assert.assertFalse(client.isApprovedForLoan());
        Assert.assertEquals("Test", client.getName());
    }

    @Test
    public void statisticsMethodReturnsTheCorrectString() {
        this.bank.addClient(new Client("Test"));
        String expected = "The client Test is at the Test bank!";
        Assert.assertEquals(expected, this.bank.statistics());
    }

    @Test
    public void statisticsMethodReturnsNoNamesWhenTheBankIsEmpty() {
        String expected = "The client  is at the Test bank!";
        Assert.assertEquals(expected, this.bank.statistics());
    }
}
