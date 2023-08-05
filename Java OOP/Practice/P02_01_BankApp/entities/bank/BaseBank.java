package bank.entities.bank;

import bank.common.ExceptionMessages;
import bank.entities.client.Client;
import bank.entities.loan.Loan;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseBank implements Bank {
    private String name;
    private int capacity;
    private Collection<Loan> loans;
    private Collection<Client> clients;

    public BaseBank(String name, int capacity) {
        this.setName(name);
        this.setCapacity(capacity);
        this.loans = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.BANK_NAME_CANNOT_BE_NULL_OR_EMPTY);
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
    public Collection<Client> getClients() {
        return this.clients;
    }

    @Override
    public Collection<Loan> getLoans() {
        return this.loans;
    }

    @Override
    public void addClient(Client client) {
        if (this.clients.size() >= this.capacity) {
            throw new IllegalStateException(ExceptionMessages.NOT_ENOUGH_CAPACITY_FOR_CLIENT);
        }
        this.clients.add(client);
    }

    @Override
    public void removeClient(Client client) {
        this.clients.remove(client);
    }

    @Override
    public void addLoan(Loan loan) {
        this.loans.add(loan);
    }

    @Override
    public int sumOfInterestRates() {
        int sum = 0;
        for (Loan loan : this.loans) {
            sum += loan.getInterestRate();
        }

        return sum;
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Name: %s, Type: %s", this.name, this.getClass().getSimpleName())).append(System.lineSeparator());

        if (this.clients.size() < 1) {
            sb.append("Clients: none").append(System.lineSeparator());
        } else {
            sb.append("Clients: ");
            int index = 0;
            for (Client client : this.clients) {
                if (index < this.clients.size() - 1) {
                    sb.append(String.format("%s, ", client.getName()));
                } else {
                    sb.append(client.getName());
                }
                index++;
            }
            sb.append(System.lineSeparator());
        }

        sb.append(String.format("Loans: %d, Sum of interest rates: %d", this.loans.size(), this.sumOfInterestRates()));

        return sb.toString().trim();
    }
}
