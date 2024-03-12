package service.impl;

import entity.Transaction;
import repository.TransactionRepository;
import service.TransactionService;

import java.util.Date;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> findByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    @Override
    public List<Transaction> findByAmountBetween(Double minAmount, Double maxAmount) {
        return transactionRepository.findByAmountBetween(minAmount,maxAmount);
    }

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> searchTransactionsInTimeInterval(Date startDate, Date endDate) {
        return transactionRepository.searchTransactionsInTimeInterval(startDate,endDate);
    }
}
