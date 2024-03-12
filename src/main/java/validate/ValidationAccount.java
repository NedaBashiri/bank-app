package validate;


import entity.BankAccount;
import exception.InsufficientFundsException;
import exception.InvalidBankAccountException;

public class ValidationAccount {

    public static void validateNonNegativeAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
    }

    public static void validationSufficientFunds(double amount, double balance) {
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds.");
        }
    }

    public static void validateBankAccount(BankAccount bankAccount) {
        if (bankAccount == null
                || bankAccount.getAccountNumber() == null
                || bankAccount.getAccountNumber().isEmpty()
                || bankAccount.getAccountHolderName() == null
                || bankAccount.getAccountHolderName().isEmpty()
                || bankAccount.getBalance() < 0) {
            throw new InvalidBankAccountException("bank account not valid.");
        }
    }
}
