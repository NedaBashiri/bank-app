package view;

import base.service.FileIO;
import base.service.impl.FileService;
import entity.BankAccount;

import entity.CheckingAccount;
import entity.SavingsAccount;
import entity.Transaction;
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
import shared.EntityManagerHelper;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class BankApp {

    public static void main(String[] args) {

        EntityManager entityManager = EntityManagerHelper.getEntityManager();

        try {
            BankAccount bankAccount  = new CheckingAccount("1235","account1",6000);
            BankAccount bankAccount1  = new SavingsAccount("1236","account2",4000);
            BankAccount bankAccount2  = new SavingsAccount("1237","account3",4000);

            EntityManagerHelper.beginTransaction();

            CheckingAccountService checkingAccountService = new CheckingAccountServiceImpl(new BankAccountRepositoryImpl<CheckingAccount>(entityManager),new CheckingAccountRepositoryImpl(entityManager),new TransactionServiceImpl(new TransactionRepositoryImpl(entityManager)));
            checkingAccountService.save((CheckingAccount) bankAccount);

            SavingsAccountService savingsAccountService = new SavingsAccountServiceImpl(new BankAccountRepositoryImpl<SavingsAccount>(entityManager),new SavingsAccountRepositoryImpl(entityManager),new TransactionServiceImpl(new TransactionRepositoryImpl(entityManager)));
            savingsAccountService.save((SavingsAccount) bankAccount1);
            savingsAccountService.save((SavingsAccount) bankAccount2);

            EntityManagerHelper.commit();

            EntityManagerHelper.beginTransaction();

            checkingAccountService.withdraw((CheckingAccount) bankAccount,50);
            List<SavingsAccount> savingsAccounts =savingsAccountService.findAll(SavingsAccount.class);
            savingsAccountService.applyInterestConcurrently(2);
            Optional<CheckingAccount> bankAccount3 = checkingAccountService.findById(CheckingAccount.class, 1L);
            System.out.println(bankAccount3.get());

            EntityManagerHelper.commit();

            List<CheckingAccount> checkingAccounts = checkingAccountService.findAll(CheckingAccount.class);
            System.out.println(checkingAccounts);
            List<SavingsAccount> accounts =savingsAccountService.findAll(SavingsAccount.class);
            System.out.println(accounts);


            BankAccountService bankAccountService = new BankAccountServiceImpl(new BankAccountRepositoryImpl(entityManager),new BankAccountRepositoryImpl(entityManager),new TransactionServiceImpl(new TransactionRepositoryImpl(entityManager)));

            Optional byAccountNumber = bankAccountService.findByAccountNumber("1236");
            System.out.println(byAccountNumber.get());

            List list = bankAccountService.searchAccountsWithBalanceGreaterThan(5000);
            System.err.println(list);

            List all = bankAccountService.findAll(BankAccount.class);
            FileIO<BankAccount> bankAccountFileIO = new FileService();
            bankAccountFileIO.saveToFile(all,"accounts.txt");
            List<BankAccount> bankAccounts = bankAccountFileIO.loadFromFile("accounts.txt");
            System.out.println(bankAccounts);

            TransactionServiceImpl transactionService = new TransactionServiceImpl(new TransactionRepositoryImpl(entityManager));
            List<Transaction> byAccountId = transactionService.findByAccountId(2l);
            System.out.println(byAccountId);

            List<Transaction> byAmountBetween = transactionService.findByAmountBetween(3000.0, 5000.0);
            System.err.println(byAmountBetween);

            List<Transaction> transactions = transactionService.searchTransactionsInTimeInterval(new Date(2024 - 03 - 01), new Date());
            System.out.println(transactions);

        } finally {
            EntityManagerHelper.closeEntityManager();

        }
    }

}
