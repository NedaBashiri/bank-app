package service.impl;

import base.repository.BaseRepository;
import entity.SavingsAccount;
import exception.InvalidTransactionException;
import repository.BankAccountRepository;
import service.SavingsAccountService;
import service.TransactionService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SavingsAccountServiceImpl extends BankAccountServiceImpl<SavingsAccount> implements SavingsAccountService {

    public SavingsAccountServiceImpl(BankAccountRepository<SavingsAccount> bankRepository, TransactionService transactionService) {
        super(bankRepository, transactionService);
    }

    @Override
    public void withdraw(SavingsAccount account, double amount) {
        try {
            lock.lock();
            validateMinimumBalance(account,amount);
            super.withdraw(account, amount);
        }finally {
            lock.unlock();
        }

    }

    @Override
    public void applyInterest(SavingsAccount account) {
        try {
            lock.lock();
            double interest = account.getBalance() * account.getInterestRate() / 12;

            super.deposit(account, interest);
        }finally {
            lock.unlock();
        }
    }


    @Override
    public void applyInterestConcurrently(int numThreads) {

        if (numThreads <= 1) {
            applyInterestSequentially();
            return;
        }

        List<SavingsAccount> accounts = bankRepository.findAll();

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        List<Callable<Void>> tasks = new ArrayList<>();
        accounts.stream().forEach(account ->   tasks.add(() -> {
            applyInterest(account);
            return null;
        }));
        try {
            executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }
    }

    private void applyInterestSequentially() {
        List<SavingsAccount> accounts = bankRepository.findAll();
        accounts.stream().forEach(this::applyInterest);
    }

    private void validateMinimumBalance(SavingsAccount account ,double amount) {
        double maximumWithdraw = account.getBalance() - account.getMinimumBalance();
        if (amount > maximumWithdraw) {
            throw new InvalidTransactionException("Can not withdraw more than " + maximumWithdraw);
        }
    }
}

