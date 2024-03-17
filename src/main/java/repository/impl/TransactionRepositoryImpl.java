package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.Transaction;
import repository.TransactionRepository;
import shared.utilities.PersistenceUnitManager;
import shared.utilities.PersistenceUnits;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class TransactionRepositoryImpl extends BaseRepositoryImpl<Transaction, Long> implements TransactionRepository {
    EntityManager em = PersistenceUnitManager.getEntityManager(PersistenceUnits.UNIT_ONE);

    public TransactionRepositoryImpl(Class<Transaction> clazz) {
        super(clazz);
    }

    @Override
    public List<Transaction> findByAccountId(Long accountId) {
        return em.createQuery("SELECT t FROM Transaction t WHERE t.bankAccount.id = :accountId", Transaction.class)
                .setParameter("accountId", accountId)
                .getResultList();
    }

    @Override
    public List<Transaction> findByAmountBetween(Double minAmount, Double maxAmount) {
        return em.createQuery("SELECT t FROM Transaction t WHERE t.amount BETWEEN :minAmount AND :maxAmount", Transaction.class)
                .setParameter("minAmount", minAmount)
                .setParameter("maxAmount", maxAmount)
                .getResultList();
    }


    @Override
    public List<Transaction> searchTransactionsInTimeInterval(Date startDate, Date endDate) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Transaction> query = cb.createQuery(Transaction.class);
        Root<Transaction> root = query.from(Transaction.class);

        Predicate predicate = cb.between(root.get("transactionDate"), startDate, endDate);

        query.select(root).where(predicate);

        TypedQuery<Transaction> typedQuery = em.createQuery(query);
        return typedQuery.getResultList();
    }

}

