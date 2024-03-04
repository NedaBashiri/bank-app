package service.impl;

import entity.BankAccount;
import repository.BankRepository;
import repository.impl.BankRepositoryImpl;
import service.BankService;

import java.io.*;
import java.util.List;
import java.util.Map;


public class BankServiceImpl<T extends BankAccount> implements BankService<T> {

    private final String FILE_NAME = "accounts.txt";

    private static BankServiceImpl instance = new BankServiceImpl();

    private BankRepository bankRepository = new BankRepositoryImpl();

    private BankServiceImpl() {
    }

    public static BankServiceImpl getInstance() {
        return instance;
    }

    @Override
    public void addAccount(T account) {
        if (account == null) {
            throw new NullPointerException("account is null.");
        }
        if (bankRepository.findAccount(account.getAccountNumber()) != null) {
            throw new IllegalArgumentException("An account with the same account number already exists.");
        }

        bankRepository.addAccount(account);

    }

    @Override
    public void removeAccount(String accountNumber) {
        bankRepository.removeAccount(accountNumber);
    }

    @Override
    public T findAccount(String accountNumber) {
        return (T) bankRepository.findAccount(accountNumber);
    }

    @Override
    public List<T> listAccounts() {
        return bankRepository.listAccounts();
    }

    @Override
    public void saveAccountsToFile() {
        try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(bankRepository.getAccounts());
            System.out.println("Accounts data has been saved to " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error saving accounts data to file: " + e.getMessage());
        }
    }


    @Override
    public void loadAccountsFromFile() {
        try (FileInputStream fileIn = new FileInputStream(FILE_NAME);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            bankRepository.setAccounts((Map<String, T>) objectIn.readObject());
            System.out.println("Accounts data has been loaded from " + FILE_NAME);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading accounts data from file: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
