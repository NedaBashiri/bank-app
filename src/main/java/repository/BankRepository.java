package repository;

import entity.BankAccount;

import java.util.List;
import java.util.Map;

public interface BankRepository<T extends BankAccount> {

    void addAccount(T account);

    void removeAccount(String accountNumber);

    BankAccount findAccount(String accountNumber);

    List<T> listAccounts();

    Map<String, T> getAccounts();

    BankData<T> setAccounts(Map<String, T> accounts);
}
