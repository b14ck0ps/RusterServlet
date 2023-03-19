package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

public class UserList extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UserList.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        var usersList = DBservices.UserRepository.getAllUsers();
        req.setAttribute("users", usersList);
        logger.info("User " + req.getSession().getAttribute("user") + " is viewing the user list");
        req.getRequestDispatcher("/Views/UserList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
