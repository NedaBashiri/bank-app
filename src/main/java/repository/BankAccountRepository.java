package repository;

import base.repository.BaseRepository;
import entity.BankAccount;
import entity.User;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository<T extends BankAccount> extends BaseRepository<T,Long> {

    List<BankAccount> searchAccountsWithBalanceGreaterThan(double minBalance);

    List<BankAccount> findAccountByUser(User user);

    Optional<BankAccount> findByAccountNumber(String accountNumber);

}