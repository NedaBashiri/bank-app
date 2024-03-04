package entity;

import exception.InsufficientFundsException;

import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class BankAccount implements Serializable {

    private String accountNumber;
    private String accountHolderName;
    private double balance;

    private Lock lock = new ReentrantLock();

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

    public synchronized void deposit(double amount) {

        lock.lock();
        try {
            if (validateNonNegativeAmount(amount)) {
                balance += amount;
                System.out.println("deposit successfully.");
            }
        } finally {
            lock.unlock();
        }

    }

    public synchronized void withdraw(double amount) {

        lock.lock();
        try {
            if (validateNonNegativeAmount(amount) && validationSufficientFunds(amount)) {
                balance -= amount;
                System.out.println("Withdraw successfully.");
            }
        } finally {
            lock.unlock();
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
