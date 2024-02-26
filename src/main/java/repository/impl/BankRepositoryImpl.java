package repository.impl;

import entity.BankAccount;
import repository.BankData;
import repository.BankRepository;

import java.util.ArrayList;
import java.util.List;

public class BankRepositoryImpl implements BankRepository {

    public BankRepositoryImpl() {

    }


    @Override
    public void addAccount(BankAccount account) {
        BankData.accounts.put(account.getAccountNumber(), account);
    }

    @Override
    public void removeAccount(String accountNumber) {
        BankData.accounts.remove(accountNumber);
    }

    @Override
    public BankAccount findAccount(String accountNumber) {
        return BankData.accounts.get(accountNumber);
    }

    @Override
    public List<BankAccount> listAccounts() {
        return new ArrayList<>(BankData.accounts.values());
    }
}
