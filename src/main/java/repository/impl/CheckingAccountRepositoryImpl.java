package repository.impl;

import entity.CheckingAccount;
import repository.CheckingAccountRepository;

import javax.persistence.EntityManager;

public class CheckingAccountRepositoryImpl extends BankAccountRepositoryImpl<CheckingAccount> implements CheckingAccountRepository {

    public CheckingAccountRepositoryImpl(EntityManager em) {
        super(em);
    }
}
