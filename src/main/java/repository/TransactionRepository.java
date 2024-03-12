package repository;


import entity.Transaction;

import java.util.Date;
import java.util.List;

public interface TransactionRepository {

    List<Transaction> findAll();

    List<Transaction> findByAccountId(Long accountId);

    List<Transaction> findByAmountBetween(Double minAmount, Double maxAmount);

    void save(Transaction transaction);

    List<Transaction> searchTransactionsInTimeInterval(Date startDate, Date endDate);
}

