package service;

import entity.BankAccount;
import interfaces.IFileIO;

import java.util.List;

public interface BankService<T extends BankAccount> extends IFileIO {

    void addAccount(T account);

    void removeAccount(String accountNumber);

    T findAccount(String accountNumber);

    List<T> listAccounts();

    double getTotalBalanceOfAccountsWithBalanceAbove(double threshold);

    void applyInterestToAllSavingsAccounts();


}
