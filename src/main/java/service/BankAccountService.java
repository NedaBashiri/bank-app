package service;

import base.service.BaseService;
import entity.BankAccount;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface BankAccountService<T extends BankAccount> extends BaseService<T> {

    void deposit(T account, double amount);

    void withdraw(T account, double amount);

    double sumBalance(List<T> accounts, Predicate<T> predicate);

    List<BankAccount> searchAccountsWithBalanceGreaterThan(double minBalance);

    Optional<BankAccount> findByAccountNumber(String accountNumber);
}
