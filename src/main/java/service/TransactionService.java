package service;

import base.service.BaseService;
import entity.Transaction;

import java.util.Date;
import java.util.List;

public interface TransactionService extends BaseService<Transaction,Long> {


    List<Transaction> findByAccountId(Long accountId);

    List<Transaction> findByAmountBetween(Double minAmount, Double maxAmount);


    List<Transaction> searchTransactionsInTimeInterval(Date startDate, Date endDate);
}