package Controllers;

import Models.UserType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

import static DBservices.UserRepository.LoginUser;
import static DBservices.UserRepository.getUserTypeByUsername;

public class Login extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Login.class.getName());

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
            logger.info("User " + username + " tried to login with empty username or password");
            return;
        }

        var user = LoginUser(username, password);
        if (user == 0) {
            req.setAttribute("invalid", "Username or password is incorrect");
            req.setAttribute("oldUsername", username);
            logger.info("User " + username + " tried to login with invalid username or password");
            req.getRequestDispatcher("/Views/Login.jsp").forward(req, resp);
        } else {
            logger.info("User " + username + " logged in successfully");
            req.getSession().setAttribute("user", username);
            var userType = getUserTypeByUsername(username);
            req.getSession().setAttribute("userType", userType.toString());
            if (userType == UserType.ADMIN) {
                logger.info("User " + username + " is an admin");
                resp.sendRedirect(req.getContextPath() + "/Admin");
            } else {
                logger.info("User " + username + " is a regular user");
                resp.sendRedirect(req.getContextPath() + "/Home");
            }
        }
    }
}
