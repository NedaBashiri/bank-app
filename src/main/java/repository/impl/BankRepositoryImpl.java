package repository.impl;

import entity.BankAccount;
import repository.BankData;
import repository.BankRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BankRepositoryImpl<T extends BankAccount> implements BankRepository<T> {

    BankData bankData = BankData.getBankData();

    public BankRepositoryImpl() {

    }


    @Override
    public void addAccount(T account) {
        bankData.getAccounts().put(account.getAccountNumber(), account);
    }


    @Override
    public void removeAccount(String accountNumber) {
        bankData.getAccounts().remove(accountNumber);
    }

    @Override
    public T findAccount(String accountNumber) {
        return (T) bankData.getAccounts().get(accountNumber);
    }

    @Override
    public List<T> listAccounts() {
        return new ArrayList<>(bankData.getAccounts().values());
    }

    @Override
    public Map<String, T> getAccounts() {
        return bankData.getAccounts();
    }

    @Override
    public BankData<T> setAccounts(Map<String, T> accounts) {
        return bankData.setAccounts(accounts);
    }
}
