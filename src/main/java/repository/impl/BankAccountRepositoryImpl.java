package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.BankAccount;
import repository.BankAccountRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class BankAccountRepositoryImpl<T extends BankAccount> extends BaseRepositoryImpl<T> implements BankAccountRepository<T> {

    protected final EntityManager em;

    public BankAccountRepositoryImpl(EntityManager em) {
        super(em);
        this.em = em;
    }


    @Override
    public List<BankAccount> searchAccountsWithBalanceGreaterThan(double minBalance) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BankAccount> query = cb.createQuery(BankAccount.class);
        Root<BankAccount> root = query.from(BankAccount.class);

        Predicate predicate = cb.greaterThan(root.get("balance"), minBalance);

        query.where(predicate);

        List<BankAccount> resultList = em.createQuery(query).getResultList();
        return resultList;
    }

    @Override
    public Optional<BankAccount> findByAccountNumber(String accountNumber) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<BankAccount> criteriaQuery = criteriaBuilder.createQuery(BankAccount.class);
        Root<BankAccount> root = criteriaQuery.from(BankAccount.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("accountNumber"), accountNumber));

        BankAccount existingAccount = em.createQuery(criteriaQuery).getSingleResult();

        return Optional.ofNullable(existingAccount);
    }

}

