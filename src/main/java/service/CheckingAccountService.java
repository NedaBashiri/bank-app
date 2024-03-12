package service;

import entity.CheckingAccount;

import java.util.Date;

public interface CheckingAccountService extends BankAccountService<CheckingAccount>{

    void deductFees(CheckingAccount account,double amount);
    void displayTotalMonthlyFees(CheckingAccount account, Date startDate, Date endDate);
}
