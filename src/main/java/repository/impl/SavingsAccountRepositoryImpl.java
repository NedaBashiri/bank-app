package repository.impl;

import entity.SavingsAccount;
import repository.SavingsAccountRepository;

import javax.persistence.EntityManager;

public class SavingsAccountRepositoryImpl extends BankAccountRepositoryImpl<SavingsAccount> implements SavingsAccountRepository {
    public SavingsAccountRepositoryImpl(EntityManager em) {
        super(em);
    }

}
