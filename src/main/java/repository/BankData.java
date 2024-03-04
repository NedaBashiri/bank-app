package repository;

import entity.BankAccount;

import java.util.HashMap;
import java.util.Map;

public final class BankData <T extends BankAccount> {

    private Map<String, T> accounts = new HashMap<>();

    private static BankData bankData = new BankData();

    private BankData() {
    }

    public static BankData getBankData(){
        return bankData;
    }

    public Map<String, T> getAccounts() {
        return accounts;
    }

    public BankData<T> setAccounts(Map<String, T> accounts) {
        this.accounts = accounts;
        return this;
    }
}
