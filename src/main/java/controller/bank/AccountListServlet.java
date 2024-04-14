package controller.bank;



import entity.BankAccount;
import entity.Transaction;
import entity.User;
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
import java.util.List;
import java.util.Optional;

public class AccountListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BankAccountService bankAccountService = new BankAccountServiceImpl(new BankAccountRepositoryImpl(BankAccount.class), new TransactionServiceImpl(new TransactionRepositoryImpl(Transaction.class)));
        UserService userService = new UserServiceImpl(new UserRepositoryImpl(User.class));
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
        try {
            List<BankAccount> accountList = bankAccountService.findAccountByUser(user);
            req.setAttribute("list", accountList);
            req.getRequestDispatcher("/bank/account.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg",e.getMessage());
            resp.sendError(707);
        }
    }
}
