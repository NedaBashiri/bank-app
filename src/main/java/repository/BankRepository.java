package repository;

import entity.BankAccount;

import java.util.List;

public interface BankRepository {

    void addAccount(BankAccount account);

    void removeAccount(String accountNumber);

    BankAccount findAccount(String accountNumber);

    List<BankAccount> listAccounts();
}
