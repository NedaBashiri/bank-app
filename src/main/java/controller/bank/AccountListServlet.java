package controller.bank;



import entity.BankAccount;
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
import java.util.List;

public class AccountListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BankAccountService bankAccountService = new BankAccountServiceImpl(new BankAccountRepositoryImpl(BankAccount.class), new TransactionServiceImpl(new TransactionRepositoryImpl(Transaction.class)));

        try {
            List<BankAccount> accountList = bankAccountService.findAll();
            req.setAttribute("list", accountList);
            req.getRequestDispatcher("/bank/account.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg",e.getMessage());
            resp.sendError(707);
        }
    }
}
