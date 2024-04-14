package entity;


import enums.AccountType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SAVINGS_ACCOUNT")
public class SavingsAccount extends BankAccount{

    private static transient double interestRate = 0.2;
    private static transient double minimumBalance = 50.0;

    public SavingsAccount() {
    }

    public SavingsAccount(String accountNumber, String accountHolderName, double balance, User user) {
        super(accountNumber, accountHolderName, balance, AccountType.SAVINGS_ACCOUNT,user);
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

}
