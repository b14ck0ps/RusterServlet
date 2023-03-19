package Controllers;

import Models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

import static DBservices.UserRepository.getUserByUsername;
import static DBservices.UserRepository.updateUser;

public class Profile extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Profile.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        var user = getUserByUsername((String) req.getSession().getAttribute("user"));
        req.setAttribute("user", user);
        if (req.getParameter("edit") != null) {
            logger.info("User " + user.getUsername() + " is editing his profile");
            req.getRequestDispatcher("/Views/ProfileEdit.jsp").forward(req, resp);
        }
        logger.info("User " + user.getUsername() + " is viewing his profile");
        req.getRequestDispatcher("/Views/Profile.jsp").forward(req, resp);
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
        var user = getUserByUsername((String) req.getSession().getAttribute("user"));
        var userType = user.getUserType();
        if (!user.getUsername().equals(username) && Validation.DatabaseValidation.usernameExists(username)) {
            setValidationError(req, username, email, "Username already exists", "username");
            isFormValid = false;
        }
        if (!user.getEmail().equals(email) && Validation.DatabaseValidation.emailExists(email)) {
            setValidationError(req, username, email, "Email already exists", "email");
            isFormValid = false;
        }
        if (isValid(req, resp, isFormValid)) {
            logger.warning("User " + user.getUsername() + " tried to edit his profile with invalid data");
            return;
        }

        if (password == null || password.isEmpty()) {
            password = getUserByUsername((String) req.getSession().getAttribute("user")).getPassword();
        } else {
            if (!Validation.FormValidation.passwordIsValid(password)) {
                setValidationError(req, username, email, "Password is not valid", "password");
                isFormValid = false;
                logger.warning("User " + user.getUsername() + " tried to edit his profile with invalid Password");
            }
        }
        //update
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        if (updateUser(user) == 1) {
            req.getSession().setAttribute("user", username);
            req.getSession().setAttribute("userType", userType.toString());
            logger.info("User " + user.getUsername() + " edited his profile");
            resp.sendRedirect(req.getContextPath() + "/Profile");
        } else {
            logger.severe("Something went wrong while updating user " + user.getUsername());
            req.setAttribute("error", "Something went wrong");
            req.getRequestDispatcher("/Views/ProfileEdit.jsp").forward(req, resp);
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
        user.setUserType(getUserByUsername((String) request.getSession().getAttribute("user")).getUserType());
        request.setAttribute("user", user);
    }

    private static boolean isValid(HttpServletRequest req, HttpServletResponse resp, boolean isFormValid) throws ServletException, IOException {
        if (!isFormValid) {
            logger.warning("User " + getUserByUsername((String) req.getSession().getAttribute("user")).getUsername() + " tried to edit his profile with invalid data");
            req.getRequestDispatcher("/Views/ProfileEdit.jsp").forward(req, resp);
            return true;
        }
        return false;
    }

}
