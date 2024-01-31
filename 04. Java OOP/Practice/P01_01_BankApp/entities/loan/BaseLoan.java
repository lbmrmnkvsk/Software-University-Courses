package bank.entities.loan;

public abstract class BaseLoan implements Loan {
    private int interestRate;
    private double amount;

    public BaseLoan(int interestRate, double amount) {
        this.setInterestRate(interestRate);
        this.setAmount(amount);
    }

    @Override
    public int getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
