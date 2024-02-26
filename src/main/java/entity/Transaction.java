package entity;

import enums.TransactionType;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {

    private TransactionType transactionType;

    private Date transactionDate;

    private double amount;

    public Transaction(TransactionType transactionType, Date transactionDate, double amount) {
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public Transaction setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public Transaction setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Transaction setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
        return this;
    }
}
