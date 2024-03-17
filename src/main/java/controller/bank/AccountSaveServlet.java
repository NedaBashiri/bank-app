package controller.bank;



import entity.BankAccount;
import entity.CheckingAccount;
import entity.SavingsAccount;
import entity.Transaction;
import repository.impl.BankAccountRepositoryImpl;
import repository.impl.TransactionRepositoryImpl;
import service.BankAccountService;
import service.impl.BankAccountServiceImpl;
import service.impl.TransactionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountSaveServlet extends HttpServlet {
    private BankAccountService bankAccountService;

    @Override
    public void init() throws ServletException {
        this.bankAccountService = new BankAccountServiceImpl(new BankAccountRepositoryImpl(BankAccount.class),new TransactionServiceImpl(new TransactionRepositoryImpl(Transaction.class)));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            BankAccount bankAccount = createBankAccount(req);
            bankAccountService.save(bankAccount);
            resp.sendRedirect("/bank/accountList");
        }catch (Exception e)
        {
            e.printStackTrace();
            req.setAttribute("msg",e.getMessage());
            resp.sendError(707);

        }
    }

    private BankAccount createBankAccount(HttpServletRequest req) {
        BankAccount bankAccount = null;
        String accountNumber = req.getParameter("accountNumber");
        String accountHolderName = req.getParameter("accountHolderName");
        Double balance = Double.valueOf(req.getParameter("balance"));
        String accountType = req.getParameter("accountType");
        if (accountType.equals("CHECKING_ACCOUNT")){
            bankAccount = new CheckingAccount(accountNumber,accountHolderName,balance);

        }else if (accountType.equals("SAVINGS_ACCOUNT")){
            bankAccount = new SavingsAccount(accountNumber,accountHolderName,balance);

        }
        return bankAccount;
    }
}
