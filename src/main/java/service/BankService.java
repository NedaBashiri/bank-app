package service;

import entity.BankAccount;
import interfaces.IFileIO;

import java.util.List;

public interface BankService extends IFileIO {

    void addAccount(BankAccount account);

    void removeAccount(String accountNumber);

    BankAccount findAccount(String accountNumber);

    List<BankAccount> listAccounts();


}
