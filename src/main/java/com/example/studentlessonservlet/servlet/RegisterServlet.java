package com.example.studentlessonservlet.servlet;

import com.example.studentlessonservlet.manager.UserManager;
import com.example.studentlessonservlet.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    private final UserManager userManager = new UserManager();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        if (userManager.getUserByEmail(email) != null) {
            req.getSession().setAttribute("msg", "Email is already existed");
            req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
        } else if (!confirmPassword.equals(password)) {
            req.getSession().setAttribute("msg", "Confirm password is wrong");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        } else {
            userManager.addUser(User.builder()
                    .username(req.getParameter("username"))
                    .email(email)
                    .password(password)
                    .build());
            req.getSession().setAttribute("user", userManager.getUserByEmail(email));
            resp.sendRedirect("/home");
        }
    }
}
