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

public class WithdrawServlet extends HttpServlet {
    private BankAccountService bankAccountService;

    @Override
    public void init() throws ServletException {
        this.bankAccountService = new BankAccountServiceImpl(new BankAccountRepositoryImpl(BankAccount.class),new TransactionServiceImpl(new TransactionRepositoryImpl(Transaction.class)));
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.valueOf(req.getParameter("id"));
            Optional bankAccountById = bankAccountService.findById(id);
            Double amount = Double.valueOf(req.getParameter("amount"));
            BankAccount bankAccount = (BankAccount) bankAccountById.get();
            bankAccountService.withdraw(bankAccount,amount);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg", e.getMessage());
            resp.sendError(707);
        }
    }

}
