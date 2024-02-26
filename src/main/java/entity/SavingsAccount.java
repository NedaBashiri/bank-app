package entity;


import exception.InvalidTransactionException;
import interfaces.Interest;

public class SavingsAccount extends BankAccount implements Interest {

    private static transient double interestRate = 0.2;
    private static transient double minimumBalance = 50.0;

    public SavingsAccount(String accountNumber, String accountHolderName, double balance) {
        super(accountNumber, accountHolderName, balance);
    }


    public double getInterestRate() {
        return interestRate;
    }

    public SavingsAccount setInterestRate(double interestRate) {
        this.interestRate = interestRate;
        return this;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public SavingsAccount setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
        return this;
    }

    @Override
    public void applyInterest() {
        double interest = (interestRate / 12) * getBalance();
        deposit(interest);
    }

    @Override
    public void withdraw(double amount) {
        if (validateNonNegativeAmount(amount) && validateMinimumBalance(amount)) {
            super.withdraw(amount);
        }
    }

    private boolean validateMinimumBalance(double amount) {
        if (getBalance() -amount < minimumBalance) {
            throw new InvalidTransactionException("Withdrawal amount exceeds minimum balance.");
        }
        return true;
    }
}
