package threads;

import entity.BankAccount;
import threads.interfaces.Task;

public class ThreadDeposit implements Task {

    private BankAccount bankAccount;
    private double amount;
    int count;

    public ThreadDeposit(BankAccount bankAccount, double amount,int count) {
        this.bankAccount = bankAccount;
        this.amount = amount;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            bankAccount.deposit(amount);
        }
    }
}
