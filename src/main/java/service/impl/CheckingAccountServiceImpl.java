package service.impl;

import base.repository.BaseRepository;
import entity.CheckingAccount;
import entity.Transaction;
import enums.TransactionType;
import exception.InsufficientFundsException;
import repository.BankAccountRepository;
import service.CheckingAccountService;
import service.TransactionService;
import validate.ValidationAccount;

import java.util.Date;

public class CheckingAccountServiceImpl extends BankAccountServiceImpl<CheckingAccount>
        implements CheckingAccountService {

    public CheckingAccountServiceImpl(BankAccountRepository<CheckingAccount> bankRepository, TransactionService transactionService) {
        super(bankRepository, transactionService);
    }

    @Override
    public void deposit(CheckingAccount account, double amount) {
        ValidationAccount.validateNonNegativeAmount(amount);

        try {
            lock.lock();
            Transaction newDeposit = new Transaction(TransactionType.DEPOSIT, new Date(), amount,10,account);
            transactionService.save(newDeposit);
            deductFees(account, amount);
            changeBalance(account, amount);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void withdraw(CheckingAccount account, double amount) {
        ValidationAccount.validateNonNegativeAmount(amount);

        try {
            lock.lock();
            validateOverdraftLimit(amount,account,10);
            Transaction newWithdraw = new Transaction(TransactionType.WITHDRAWAL, new Date(), amount, 10,account);
            transactionService.save(newWithdraw);
            deductFees(account, 10);
            changeBalance(account, -amount);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void deductFees(CheckingAccount account, double fee) {
        changeBalance(account, -fee);
    }

    @Override
    public void displayTotalMonthlyFees(CheckingAccount account, Date startDate, Date endDate) {

        double feeTotal = transactionService.findAll().stream()
                .filter(trx -> trx.getTransactionDate().after(startDate) && trx.getTransactionDate().before(endDate))
                .mapToDouble(Transaction::getFee)
                .sum();
        System.out.println("Fee total: " + feeTotal);
    }



    private void validateOverdraftLimit(double amount, CheckingAccount checkingAccount,double fee) {
        double balance = checkingAccount.getBalance();
        balance += checkingAccount.getOverdraftLimit();
        if (amount + fee > balance) {
            throw new InsufficientFundsException("Withdrawal amount exceeds overdraft limit.");
        }
    }
}
