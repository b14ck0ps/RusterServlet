package Controllers;

import Models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Profile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        var user = DBservices.DatabaseOperations.getUserByUsername((String) req.getSession().getAttribute("user"));
        req.setAttribute("user", user);
        if (req.getParameter("edit") != null) {
            req.getRequestDispatcher("ProfileEdit.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("Profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var username = req.getParameter("username");
        var email = req.getParameter("email");
        var password = req.getParameter("password");

        String[] fields = {username, email};
        String[] fieldNames = {"username", "email"};

        boolean isFormValid = true;

        // Checking if all fields are filled
        for (int i = 0; i < fields.length; i++) {
            if (!Validation.FormValidation.inputExist(fields[i])) {
                String errorMessage = fieldNames[i] + " is required";
                setValidationError(req, username, email, errorMessage, fieldNames[i]);
                isFormValid = false;
            }
        }
        if (isValid(req, resp, isFormValid)) return;
        // Checking if fields are valid
        if (!Validation.FormValidation.emailIsValid(email)) {
            setValidationError(req, username, email, "Email is not valid", "email");
            isFormValid = false;
        }
        if (isValid(req, resp, isFormValid)) return;
        // Checking if fields are unique
        var user = DBservices.DatabaseOperations.getUserByUsername((String) req.getSession().getAttribute("user"));
        var userType = user.getUserType();
        if (!user.getUsername().equals(username) && Validation.DatabaseValidation.usernameExists(username)) {
            setValidationError(req, username, email, "Username already exists", "username");
            isFormValid = false;
        }
        if (!user.getEmail().equals(email) && Validation.DatabaseValidation.emailExists(email)) {
            setValidationError(req, username, email, "Email already exists", "email");
            isFormValid = false;
        }
        if (isValid(req, resp, isFormValid)) return;

        if (password == null || password.isEmpty()) {
            password = DBservices.DatabaseOperations.getUserByUsername((String) req.getSession().getAttribute("user")).getPassword();
        } else {
            if (!Validation.FormValidation.passwordIsValid(password)) {
                setValidationError(req, username, email, "Password is not valid", "password");
                isFormValid = false;
            }
        }
        //update
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        if (DBservices.DatabaseOperations.updateUser(user) == 1) {
            req.getSession().setAttribute("user", username);
            req.getSession().setAttribute("userType", userType.toString());
            resp.sendRedirect("/Profile");
        } else {
            req.setAttribute("error", "Something went wrong");
            req.getRequestDispatcher("ProfileEdit.jsp").forward(req, resp);
        }
    }

    private void setValidationError(HttpServletRequest request, String username, String email, String errorMessage, String fieldName) {
        switch (fieldName) {
            case "username" -> {
                request.setAttribute("usernameError", errorMessage);
            }
            case "email" -> {
                request.setAttribute("emailError", errorMessage);
            }
        }
        var user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setUserType(DBservices.DatabaseOperations.getUserByUsername((String) request.getSession().getAttribute("user")).getUserType());
        request.setAttribute("user", user);
    }

    private static boolean isValid(HttpServletRequest req, HttpServletResponse resp, boolean isFormValid) throws ServletException, IOException {
        if (!isFormValid) {
            req.getRequestDispatcher("ProfileEdit.jsp").forward(req, resp);
            return true;
        }
        return false;
    }

}
