package view;

import base.service.FileIO;
import base.service.impl.FileService;
import entity.*;

import repository.impl.BankAccountRepositoryImpl;
import repository.impl.CheckingAccountRepositoryImpl;
import repository.impl.SavingsAccountRepositoryImpl;
import repository.impl.TransactionRepositoryImpl;
import service.BankAccountService;
import service.CheckingAccountService;
import service.SavingsAccountService;
import service.impl.BankAccountServiceImpl;
import service.impl.CheckingAccountServiceImpl;
import service.impl.SavingsAccountServiceImpl;
import service.impl.TransactionServiceImpl;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class BankApp {

    private static final Logger LOGGER = Logger.getLogger(BankApp.class.getName());

    public static void main(String[] args) {

            BankAccount bankAccount  = new CheckingAccount("1235","account1",6000);
            BankAccount bankAccount1  = new SavingsAccount("1236","account2",4000);
            BankAccount bankAccount2  = new SavingsAccount("1237","account3",4000);


            CheckingAccountService checkingAccountService = new CheckingAccountServiceImpl(new BankAccountRepositoryImpl<CheckingAccount>(CheckingAccount.class),new TransactionServiceImpl(new TransactionRepositoryImpl(Transaction.class)));
            checkingAccountService.save((CheckingAccount) bankAccount);

            SavingsAccountService savingsAccountService = new SavingsAccountServiceImpl(new BankAccountRepositoryImpl<SavingsAccount>(SavingsAccount.class),new TransactionServiceImpl(new TransactionRepositoryImpl(Transaction.class)));
            savingsAccountService.save((SavingsAccount) bankAccount1);
            savingsAccountService.save((SavingsAccount) bankAccount2);


            checkingAccountService.withdraw((CheckingAccount) bankAccount,50);
            List<SavingsAccount> savingsAccounts =savingsAccountService.findAll();
            savingsAccountService.applyInterestConcurrently(2);
            Optional<CheckingAccount> bankAccount3 = checkingAccountService.findById(1L);
            LOGGER.info(bankAccount3.toString());


            List<CheckingAccount> checkingAccounts = checkingAccountService.findAll();
            LOGGER.info(checkingAccounts.toString());
            List<SavingsAccount> accounts =savingsAccountService.findAll();
            LOGGER.info(accounts.toString());


            BankAccountService bankAccountService = new BankAccountServiceImpl(new BankAccountRepositoryImpl(BankAccount.class),new TransactionServiceImpl(new TransactionRepositoryImpl(Transaction.class)));


            Optional byAccountNumber = bankAccountService.findByAccountNumber("1236");
            LOGGER.info(byAccountNumber.toString());

            List list = bankAccountService.searchAccountsWithBalanceGreaterThan(5000);
            LOGGER.info(list.toString());

            List all = bankAccountService.findAll();
            FileIO<BankAccount> bankAccountFileIO = new FileService();
            bankAccountFileIO.saveToFile(all,"accounts.txt");
            List<BankAccount> bankAccounts = bankAccountFileIO.loadFromFile("accounts.txt");
            LOGGER.info(bankAccounts.toString());

            TransactionServiceImpl transactionService = new TransactionServiceImpl(new TransactionRepositoryImpl(Transaction.class));
            List<Transaction> byAccountId = transactionService.findByAccountId(2l);
            LOGGER.info(byAccountId.toString());

            List<Transaction> byAmountBetween = transactionService.findByAmountBetween(3000.0, 5000.0);
            LOGGER.info(byAmountBetween.toString());

            List<Transaction> transactions = transactionService.searchTransactionsInTimeInterval(new Date(2024 - 03 - 01), new Date());
            LOGGER.info(transactions.toString());

    }

}
