package controller;


import entity.User;
import org.hibernate.exception.DataException;
import repository.impl.UserRepositoryImpl;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            UserService userService = new UserServiceImpl(new UserRepositoryImpl(User.class));
        String message = "";
        User user = new User();
        try {
            user.setName(req.getParameter("name"));
            user.setEmail(req.getParameter("email"));
            user.setPassword(req.getParameter("password"));
             message = userService.createUser(user);
        } catch (DataException e) {
            message = "Wrong entered data format for " + e.getMessage() + "!";
        }


            req.setAttribute("message", message);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("Register.jsp");
            requestDispatcher.forward(req, resp);

    }
}
