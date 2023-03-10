package Controllers;

import Models.UserType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Registration extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("Registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var username = req.getParameter("username");
        var password = req.getParameter("password");
        var email = req.getParameter("email");
        var userType = req.getParameter("UserType");

        //validate the data individually
        if (username == null || username.isEmpty()) {
            req.setAttribute("usernameError", "Username is required");

        }
        if (password == null || password.isEmpty()) {
            req.setAttribute("passwordError", "Password is required");

        }
        if (email == null || email.isEmpty()) {
            req.setAttribute("emailError", "Email is required");
        }

        if (username == null || username.isEmpty() || password == null || password.isEmpty() || email == null || email.isEmpty()) {
            req.setAttribute("oldEmail" , email);
            req.setAttribute("oldUsername" , username);
            req.setAttribute("oldPassword" , password);
            req.getRequestDispatcher("Registration.jsp").forward(req, resp);
            return;
        }
        UserType Type;
        if (userType.equals("admin")) {
            Type = UserType.ADMIN;
        } else {
            Type = UserType.CUSTOMER;
        }

        var user = new Models.User(username, password, email, Type);
        DBservices.DatabaseOperations.RegisterUser(user);
        if (DBservices.DatabaseOperations.RegisterUser(user) == 1) {
            req.getRequestDispatcher("Login.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("Registration.jsp").forward(req, resp);
        }
    }
}
