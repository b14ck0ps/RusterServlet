package Controllers;

import Models.UserType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static DBservices.UserRepository.LoginUser;
import static DBservices.UserRepository.getUserTypeByUsername;

public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/Views/Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var username = req.getParameter("username");
        var password = req.getParameter("password");

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            req.setAttribute("loginError", "Username or password is incorrect");
            req.getRequestDispatcher("/Views/Login.jsp").forward(req, resp);
            return;
        }

        var user = LoginUser(username, password);
        if (user == 0) {
            req.setAttribute("invalid", "Username or password is incorrect");
            req.setAttribute("oldUsername", username);
            req.getRequestDispatcher("/Views/Login.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("user", username);
            var userType = getUserTypeByUsername(username);
            req.getSession().setAttribute("userType", userType.toString());
            if (userType == UserType.ADMIN) {
                resp.sendRedirect("Admin");
            } else {
                resp.sendRedirect("Home");
            }
        }
    }
}
