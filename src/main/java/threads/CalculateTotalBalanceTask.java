package threads;

import entity.BankAccount;
import threads.interfaces.Task;

import java.util.List;

public class CalculateTotalBalanceTask implements Task {

    private List<BankAccount> accounts;

    public CalculateTotalBalanceTask(List<BankAccount> accounts) {
        this.accounts = accounts;
    }

    @Override
    public void run() {
        double totalBalance = 0;
        for (BankAccount account : accounts) {
            totalBalance += account.getBalance();
        }
        System.out.println("totalBalance is: " + totalBalance);
    }
}
