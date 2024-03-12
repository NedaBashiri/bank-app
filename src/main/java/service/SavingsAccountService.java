package service;

import entity.SavingsAccount;

public interface SavingsAccountService extends BankAccountService<SavingsAccount>{
    void applyInterest(SavingsAccount account);

    void applyInterestConcurrently(int numThreads);
}