package threads;

import entity.BankAccount;
import threads.interfaces.Task;

public class ThreadWithdrawal implements Task {

    BankAccount bankAccount;
    double amount;
    int count;

    public ThreadWithdrawal(BankAccount bankAccount,double amount,int count) {
        this.bankAccount = bankAccount;
        this.amount = amount;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            bankAccount.withdraw(amount);
        }
    }

}
