package entity;

import enums.TransactionType;
import exception.InsufficientFundsException;
import interfaces.Fees;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckingAccount extends BankAccount implements Fees {


    private static final transient double TRANSACTION_FEE = 0.2;

    private double overdraftLimit = 1000;


    List<Transaction> transactionList = new ArrayList<>();


    public CheckingAccount(String accountNumber, String accountHolderName, double balance) {
        super(accountNumber, accountHolderName, balance);

    }

    @Override
    public synchronized void deposit(double amount) {
        if (validateNonNegativeAmount(amount)) {
            Transaction newDeposit = new Transaction(TransactionType.DEPOSIT, new Date(), amount);
            transactionList.add(newDeposit);
            super.deposit(amount);
            deductFees(amount);
        }
    }

    @Override
    public synchronized void withdraw(double amount) {
        double amountWithFee = amount + calculateTransactionFee(amount);

        if (validateOverdraftLimit(amountWithFee)) {

            Transaction newWithdraw = new Transaction(TransactionType.WITHDRAWAL, new Date(), amount);
            transactionList.add(newWithdraw);
            super.withdraw(amount);
            deductFees(amount);
        }

    }

    @Override
    public void deductFees(double amount) {
        super.withdraw(calculateTransactionFee(amount));
    }

    @Override
    public void displayTotalMonthlyFees(Date startDate, Date endDate) {
        double total = 0.0;
        for (Transaction transaction : transactionList) {
            if (transaction.getTransactionDate().after(startDate) && transaction.getTransactionDate().before(endDate)) {
                total += calculateTransactionFee(transaction.getAmount());
            }
        }
        System.out.println("total: " + total);
    }

    private double calculateTransactionFee(double amount) {

        return amount * TRANSACTION_FEE;
    }

    private boolean validateOverdraftLimit(double amount) {
        if (amount > getBalance() + overdraftLimit) {
            throw new InsufficientFundsException("Withdrawal amount exceeds overdraft limit.");
        }
        return true;
    }

}
