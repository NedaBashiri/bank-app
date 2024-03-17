package entity;

import base.entity.BaseEntity;
import enums.AccountType;
import validate.ValidationAccount;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BankAccount extends BaseEntity<Long> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "account_number",unique = true)
    private String accountNumber;
    @Column(name = "account_holder_name")
    private String accountHolderName;
    @Column(name = "balance")
    private double balance;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", insertable = false, updatable = false)
    private AccountType accountType;

    public BankAccount() {

    }

    public BankAccount(String accountNumber, String accountHolderName, double newBalance, AccountType accountType) {
        this();
        ValidationAccount.validateNonNegativeAmount(newBalance);
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        this.balance += newBalance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankAccount that = (BankAccount) o;

        if (Double.compare(that.balance, balance) != 0) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (accountNumber != null ? !accountNumber.equals(that.accountNumber) : that.accountNumber != null)
            return false;
        if (accountHolderName != null ? !accountHolderName.equals(that.accountHolderName) : that.accountHolderName != null)
            return false;
        return accountType == that.accountType;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (accountNumber != null ? accountNumber.hashCode() : 0);
        result = 31 * result + (accountHolderName != null ? accountHolderName.hashCode() : 0);
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (accountType != null ? accountType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", balance=" + balance +
                '}'+ "\n";
    }
}