package repository.impl;

import entity.SavingsAccount;
import repository.SavingsAccountRepository;

public class SavingsAccountRepositoryImpl extends BankAccountRepositoryImpl<SavingsAccount> implements SavingsAccountRepository {
    public SavingsAccountRepositoryImpl(Class<SavingsAccount> clazz) {
        super(clazz);
    }
}
