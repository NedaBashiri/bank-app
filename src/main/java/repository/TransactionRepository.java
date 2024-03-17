package repository;


import base.repository.BaseRepository;
import entity.Transaction;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends BaseRepository<Transaction,Long> {

    List<Transaction> findByAccountId(Long accountId);

    List<Transaction> findByAmountBetween(Double minAmount, Double maxAmount);

    List<Transaction> searchTransactionsInTimeInterval(Date startDate, Date endDate);
}

