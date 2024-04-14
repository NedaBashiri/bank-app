package controller.bank;



import entity.*;
import repository.impl.BankAccountRepositoryImpl;
import repository.impl.TransactionRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import service.BankAccountService;
import service.UserService;
import service.impl.BankAccountServiceImpl;
import service.impl.TransactionServiceImpl;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class AccountSaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BankAccountService bankAccountService = new BankAccountServiceImpl(new BankAccountRepositoryImpl(BankAccount.class), new TransactionServiceImpl(new TransactionRepositoryImpl(Transaction.class)));

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
        UserService userService = new UserServiceImpl(new UserRepositoryImpl(User.class));
        BankAccount bankAccount = null;
        String accountNumber = req.getParameter("accountNumber");
        String accountHolderName = req.getParameter("accountHolderName");
        Double balance = Double.valueOf(req.getParameter("balance"));
        String accountType = req.getParameter("accountType");
        Cookie[] cookies = req.getCookies();
        String email = null;
        if (cookies != null) {
            Cookie emailCookie = Arrays.stream(cookies)
                    .filter(cookie -> "email".equals(cookie.getName()))
                    .findFirst()
                    .orElse(null);

            if (emailCookie != null) {
                email = emailCookie.getValue();
            }
        }
        Optional<User> userByEmail = userService.findUserByEmail(email);
        User user = userByEmail.get();


        if (accountType.equals("CHECKING_ACCOUNT")){
            bankAccount = new CheckingAccount(accountNumber,accountHolderName,balance,user);

        }else if (accountType.equals("SAVINGS_ACCOUNT")){
            bankAccount = new SavingsAccount(accountNumber,accountHolderName,balance,user);

        }
        return bankAccount;
    }
}
