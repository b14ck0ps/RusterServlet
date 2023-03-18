package Controllers;

import DBservices.DatabasesConnection;
import Models.UserType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static DBservices.UserRepository.RegisterUser;

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

        String[] fields = {username, password, email};
        String[] fieldNames = {"username", "password", "email"};

        boolean isFormValid = true;

        // Checking if all fields are filled
        for (int i = 0; i < fields.length; i++) {
            if (!Validation.FormValidation.inputExist(fields[i])) {
                String errorMessage = fieldNames[i] + " is required";
                setValidationError(req, username, password, email, errorMessage, fieldNames[i]);
                isFormValid = false;
            }
        }
        if (isValid(req, resp, isFormValid)) return;
        // Checking if fields are valid
        if (!Validation.FormValidation.emailIsValid(email)) {
            setValidationError(req, username, password, email, "Email is not valid", "email");
            isFormValid = false;
        }
        if (!Validation.FormValidation.passwordIsValid(password)) {
            setValidationError(req, username, password, email, "Password is not valid", "password");
            isFormValid = false;
        }
        if (isValid(req, resp, isFormValid)) return;
        // Checking if fields are unique
        if (Validation.DatabaseValidation.usernameExists(username)) {
            setValidationError(req, username, password, email, "Username already exists", "username");
            isFormValid = false;
        }
        if (Validation.DatabaseValidation.emailExists(email)) {
            setValidationError(req, username, password, email, "Email already exists", "email");
            isFormValid = false;
        }
        if (isValid(req, resp, isFormValid)) return;
        // Registering user
        UserType Type = userType == null || userType.equals("Customer") ? UserType.CUSTOMER : UserType.ADMIN;
        var user = new Models.User(username, password, email, Type);
        if (RegisterUser(user) == 1) {
            req.getRequestDispatcher("Login.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("Registration.jsp").forward(req, resp);
        }
    }

    private static boolean isValid(HttpServletRequest req, HttpServletResponse resp, boolean isFormValid) throws ServletException, IOException {
        if (!isFormValid) {
            req.getRequestDispatcher("Registration.jsp").forward(req, resp);
            return true;
        }
        return false;
    }

    private void setValidationError(HttpServletRequest request, String username, String password, String email, String errorMessage, String fieldName) {
        switch (fieldName) {
            case "username" -> {
                request.setAttribute("usernameError", errorMessage);
            }
            case "password" -> {
                request.setAttribute("passwordError", errorMessage);
            }
            case "email" -> {
                request.setAttribute("emailError", errorMessage);
            }
        }
        request.setAttribute("oldUsername", username);
        request.setAttribute("oldPassword", password);
        request.setAttribute("oldEmail", email);
    }
}
