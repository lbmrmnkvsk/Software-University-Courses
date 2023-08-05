package bank.core;

import bank.common.ConstantMessages;
import bank.common.ExceptionMessages;
import bank.entities.bank.Bank;
import bank.entities.bank.BranchBank;
import bank.entities.bank.CentralBank;
import bank.entities.client.Adult;
import bank.entities.client.Client;
import bank.entities.client.Student;
import bank.entities.loan.Loan;
import bank.entities.loan.MortgageLoan;
import bank.entities.loan.StudentLoan;
import bank.repositories.LoanRepository;


import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller{
    private LoanRepository loans;
    private Collection<Bank> banks;

    public ControllerImpl() {
        this.loans = new LoanRepository();
        this.banks = new ArrayList<>();
    }

    @Override
    public String addBank(String type, String name) {
        if (!type.equals("BranchBank") && !type.equals("CentralBank")) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_BANK_TYPE);
        }

        Bank bank = null;
        if (type.equals("BranchBank")) {
            bank = new BranchBank(name);
        } else if (type.equals("CentralBank")) {
            bank = new CentralBank(name);
        }

        this.banks.add(bank);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_BANK_OR_LOAN_TYPE, type);
    }

    @Override
    public String addLoan(String type) {
        if (!type.equals("StudentLoan") && !type.equals("MortgageLoan")) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_LOAN_TYPE);
        }

        Loan loan = null;
        if (type.equals("StudentLoan")) {
            loan = new StudentLoan();
        } else if (type.equals("MortgageLoan")) {
            loan = new MortgageLoan();
        }

        this.loans.addLoan(loan);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_BANK_OR_LOAN_TYPE, type);
    }

    @Override
    public String returnedLoan(String bankName, String loanType) {
        Bank bank = this.banks.stream().filter(b -> b.getName().equals(bankName)).findFirst().orElse(null);
        Loan loan = this.loans.findFirst(loanType);
        if (loan == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_LOAN_FOUND, loanType));
        }

        bank.addLoan(loan);
        this.loans.removeLoan(loan);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CLIENT_OR_LOAN_TO_BANK, loanType, bankName);
    }

    @Override
    public String addClient(String bankName, String clientType, String clientName, String clientID, double income) {
        if (!clientType.equals("Adult") && !clientType.equals("Student")) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_CLIENT_TYPE);
        }

        Bank bank = this.banks.stream().filter(b -> b.getName().equals(bankName)).findFirst().orElse(null);
        Client client = null;
        if (clientType.equals("Adult")) {
            client = new Adult(clientName, clientID, income);
        } else if (clientType.equals("Student")) {
            client = new Student(clientName, clientID, income);
        }

        if ( (clientType.equals("Adult") && bank.getClass().getSimpleName().equals("CentralBank")) ||
                (clientType.equals("Student") && bank.getClass().getSimpleName().equals("BranchBank")) ) {
            bank.addClient(client);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CLIENT_OR_LOAN_TO_BANK, clientType, bankName);
        } else {
            return ConstantMessages.UNSUITABLE_BANK;
        }
    }

    @Override
    public String finalCalculation(String bankName) {
        Bank bank = this.banks.stream().filter(b -> b.getName().equals(bankName)).findFirst().orElse(null);
        double sum = 0;

        for (Client client : bank.getClients()) {
            sum += client.getIncome();
        }

        for (Loan loan : bank.getLoans()) {
            sum += loan.getAmount();
        }

        return String.format(ConstantMessages.FUNDS_BANK, bankName, sum);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        for (Bank bank : this.banks) {
            sb.append(bank.getStatistics()).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
