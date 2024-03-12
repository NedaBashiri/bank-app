package repository;

import base.repository.BaseRepository;
import entity.BankAccount;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository<T extends BankAccount> extends BaseRepository<T> {

    List<BankAccount> searchAccountsWithBalanceGreaterThan(double minBalance);

    Optional<BankAccount> findByAccountNumber(String accountNumber);

}