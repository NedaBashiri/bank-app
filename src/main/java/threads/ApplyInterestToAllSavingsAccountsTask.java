package threads;

import entity.BankAccount;
import entity.SavingsAccount;
import threads.interfaces.Task;

import java.util.ArrayList;
import java.util.List;

public class ApplyInterestToAllSavingsAccountsTask implements Task {

    private List<SavingsAccount> savingsAccounts = new ArrayList<>();

    public ApplyInterestToAllSavingsAccountsTask(List<BankAccount> accounts) {
        for (BankAccount account : accounts) {
            if (account instanceof SavingsAccount) {
                savingsAccounts.add((SavingsAccount) account);
            }
        }
    }

    @Override
    public void run() {
        for (SavingsAccount account : savingsAccounts) {
            account.applyInterest();

        }
    }
}
