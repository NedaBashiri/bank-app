package service.impl;

import base.repository.BaseRepository;
import base.service.impl.BaseServiceImpl;
import entity.BankAccount;
import entity.Transaction;
import entity.User;
import enums.TransactionType;
import repository.BankAccountRepository;
import service.BankAccountService;
import service.TransactionService;
import validate.ValidationAccount;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

public class BankAccountServiceImpl<T extends BankAccount> extends BaseServiceImpl<T,Long,BankAccountRepository<T>> implements BankAccountService<T> {

    protected final Lock lock = new ReentrantLock();
    protected BankAccountRepository<T> bankRepository;
    protected TransactionService transactionService;

    public BankAccountServiceImpl( BankAccountRepository<T> bankRepository, TransactionService transactionService) {
        super(bankRepository);
        this.bankRepository = bankRepository;
        this.transactionService = transactionService;
    }

    @Override
    public void save(T account) {
        ValidationAccount.validateBankAccount(account);
        bankRepository.persist(account);
    }

    public void changeBalance(T account, double amount) {

        try {
            lock.lock();
            account.setBalance(account.getBalance() + amount);
            update(account);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void deposit(T account, double amount) {
        ValidationAccount.validateNonNegativeAmount(amount);
        Transaction newDeposit = new Transaction(TransactionType.DEPOSIT, new Date(), amount, account);
        transactionService.save(newDeposit);
        changeBalance(account, amount);
    }

    @Override
    public void withdraw(T account, double amount) {
        ValidationAccount.validateNonNegativeAmount(amount);
        ValidationAccount.validationSufficientFunds(amount, account.getBalance());
        Transaction newWithdraw = new Transaction(TransactionType.DEPOSIT, new Date(), amount, account);
        transactionService.save(newWithdraw);
        changeBalance(account, -amount);
    }

    @Override
    public double sumBalance(List<T> accounts, Predicate<T> predicate) {
        return accounts.stream()
                .filter(predicate)
                .mapToDouble(BankAccount::getBalance)
                .sum();
    }

    @Override
    public List<BankAccount> searchAccountsWithBalanceGreaterThan(double minBalance) {
        return bankRepository.searchAccountsWithBalanceGreaterThan(minBalance);
    }

    @Override
    public Optional<BankAccount> findByAccountNumber(String accountNumber) {
        return bankRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public List<BankAccount> findAccountByUser(User user) {
        return bankRepository.findAccountByUser(user);
    }
}