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
import java.util.Optional;

public class DepositServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BankAccountService bankAccountService = new BankAccountServiceImpl(new BankAccountRepositoryImpl(BankAccount.class), new TransactionServiceImpl(new TransactionRepositoryImpl(Transaction.class)));

        try {
            Long id = Long.valueOf(req.getParameter("id"));
            Double amount = Double.valueOf(req.getParameter("amount"));
            Optional bankAccountById = bankAccountService.findById(id);
            BankAccount bankAccount = (BankAccount) bankAccountById.get();
            bankAccountService.deposit(bankAccount,amount);
            resp.sendRedirect("/bank/accountList");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg", e.getMessage());
            resp.sendError(707);
        }
    }
}
