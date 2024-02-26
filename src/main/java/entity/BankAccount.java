package entity;

import exception.InsufficientFundsException;

import java.io.Serializable;

public abstract class BankAccount implements Serializable {

    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public BankAccount() {
    }

    public BankAccount(String accountNumber, String accountHolderName, double newBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        if (validateNonNegativeAmount(newBalance)) {
            this.balance += newBalance;
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void deposit(double amount) {
        if (validateNonNegativeAmount(amount)) {
            balance += amount;
            System.out.println("deposit successfully.");
        }
    }

    public void withdraw(double amount) {
        if (validateNonNegativeAmount(amount) && validationSufficientFunds(amount)) {
            balance -= amount;
            System.out.println("Withdraw successfully.");
        }
    }

    protected boolean validateNonNegativeAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        return true;
    }

    protected boolean validationSufficientFunds(double amount) {
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds.");
        }
        return true;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
