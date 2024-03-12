package entity;


import enums.AccountType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CHECKING_ACCOUNT")
public class CheckingAccount extends BankAccount{

    private double overdraftLimit = 1000;

    public CheckingAccount() {
    }

    public CheckingAccount(String accountNumber, String accountHolderName, double balance ) {
        super(accountNumber, accountHolderName, balance, AccountType.CHECKING_ACCOUNT);

    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }


}