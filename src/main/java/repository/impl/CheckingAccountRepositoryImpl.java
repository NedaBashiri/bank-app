package repository.impl;

import entity.CheckingAccount;
import repository.CheckingAccountRepository;

public class CheckingAccountRepositoryImpl extends BankAccountRepositoryImpl<CheckingAccount> implements CheckingAccountRepository {

    public CheckingAccountRepositoryImpl(Class<CheckingAccount> clazz) {
        super(clazz);
    }
}