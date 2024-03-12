package entity;

import enums.TransactionType;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {

    private Long id;

    private TransactionType transactionType;

    private Date transactionDate;

    private double amount;

    private double fee;

    private BankAccount bankAccount;

    public Transaction() {
    }

    public Transaction(TransactionType transactionType, Date transactionDate, double amount, BankAccount bankAccount) {
        this(transactionType, transactionDate, amount, 0d, bankAccount);
    }

    public Transaction(TransactionType transactionType, Date transactionDate, double amount, double fee, BankAccount bankAccount) {
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.fee = fee;
        this.bankAccount = bankAccount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (Double.compare(that.amount, amount) != 0) return false;
        if (Double.compare(that.fee, fee) != 0) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (transactionType != that.transactionType) return false;
        if (transactionDate != null ? !transactionDate.equals(that.transactionDate) : that.transactionDate != null)
            return false;
        return bankAccount != null ? bankAccount.equals(that.bankAccount) : that.bankAccount == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (transactionType != null ? transactionType.hashCode() : 0);
        result = 31 * result + (transactionDate != null ? transactionDate.hashCode() : 0);
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(fee);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (bankAccount != null ? bankAccount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionType=" + transactionType +
                ", transactionDate=" + transactionDate +
                ", amount=" + amount +
                ", fee=" + fee +
                ", bankAccount=" + bankAccount +
                '}';
    }
}
