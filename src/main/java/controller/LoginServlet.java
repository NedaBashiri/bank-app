package controller;

import entity.User;
import repository.impl.UserRepositoryImpl;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String destPage = "Login.jsp";
        String message = "";
        try {
            if (req.getParameter("email").equals("") || req.getParameter("password").equals("")) {
                message = "Name And Password Required";
            } else {
                UserService userService = new UserServiceImpl(new UserRepositoryImpl(User.class));
                String email = req.getParameter("email");
                Optional<User> optionalUser = userService.findUserByEmail(email);
                User user = optionalUser.get();
                if (req.getParameter("password").equals(user.getPassword())) {

                    String password = req.getParameter("password");

                    req.setAttribute("currentUser", user);

                    Cookie cookie_email = new Cookie("email", email);
                    Cookie cookie_password = new Cookie("password", password);
                    Cookie cookie_isUserLogin = new Cookie("isUserLogin", "true");
                    resp.addCookie(cookie_email);
                    resp.addCookie(cookie_password);
                    resp.addCookie(cookie_isUserLogin);

                    destPage = "index.jsp";
                } else {
                    message = "Wrong Password or email";
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg", e.getMessage());
            resp.sendError(707);
        }

        req.setAttribute("message", message);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(destPage);
        requestDispatcher.forward(req, resp);

    }
}
