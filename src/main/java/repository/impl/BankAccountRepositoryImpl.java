package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.BankAccount;
import entity.User;
import repository.BankAccountRepository;
import shared.utilities.PersistenceUnitManager;
import shared.utilities.PersistenceUnits;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class BankAccountRepositoryImpl<T extends BankAccount> extends BaseRepositoryImpl<T,Long> implements BankAccountRepository<T> {

    EntityManager em = PersistenceUnitManager.getEntityManager(PersistenceUnits.UNIT_ONE);

    public BankAccountRepositoryImpl(Class<T> clazz) {
        super(clazz);
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
    public List<BankAccount> findAccountByUser(User user) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<BankAccount> criteriaQuery = criteriaBuilder.createQuery(BankAccount.class);
        Root<BankAccount> root = criteriaQuery.from(BankAccount.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("user"), user));

        TypedQuery<BankAccount> query = em.createQuery(criteriaQuery);
        return query.getResultList();
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

