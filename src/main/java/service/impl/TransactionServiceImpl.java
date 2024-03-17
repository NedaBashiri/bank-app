package service.impl;

import base.service.impl.BaseServiceImpl;
import entity.Transaction;
import repository.TransactionRepository;
import service.TransactionService;

import java.util.Date;
import java.util.List;

public class TransactionServiceImpl extends BaseServiceImpl<Transaction, Long, TransactionRepository> implements TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        super(transactionRepository);
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> findByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    @Override
    public List<Transaction> findByAmountBetween(Double minAmount, Double maxAmount) {
        return transactionRepository.findByAmountBetween(minAmount, maxAmount);
    }

    @Override
    public List<Transaction> searchTransactionsInTimeInterval(Date startDate, Date endDate) {
        return transactionRepository.searchTransactionsInTimeInterval(startDate, endDate);
    }
}
