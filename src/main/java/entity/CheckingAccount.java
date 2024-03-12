package entity;


public class CheckingAccount extends BankAccount{

    private double overdraftLimit = 1000;

    public CheckingAccount() {
    }

    public CheckingAccount(String accountNumber, String accountHolderName, double balance ) {
        super(accountNumber, accountHolderName, balance, AccountType.CHECKING_ACCOUNT);

    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }


}